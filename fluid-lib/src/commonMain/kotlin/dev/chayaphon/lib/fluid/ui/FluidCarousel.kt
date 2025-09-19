package dev.chayaphon.lib.fluid.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.toSize
import dev.chayaphon.lib.fluid.FluidAnimationController
import kotlin.math.abs
import kotlin.math.sign

/**
 * FluidCarousel - A carousel with fluid-edge animations during swipe
 */
@Composable
fun FluidCarousel(
    children: List<@Composable () -> Unit>,
    enableBubble: Boolean = false,
    modifier: Modifier = Modifier
) {

    if (children.isEmpty()) return

    var currentIndex by remember { mutableStateOf(0) }
    var dragIndex by remember { mutableStateOf<Int?>(null) }
    var dragStartPosition by remember { mutableStateOf(Offset.Zero) }
    var dragDirection by remember { mutableStateOf(0f) }
    var dragCompleted by remember { mutableStateOf(false) }
    var containerSize by remember { mutableStateOf(IntSize.Zero) }

    // Fluid edge animation state
    val fluidEdge = remember { FluidEdge(count = 25) }

    val fluidAnimationController = remember { FluidAnimationController() }

    // Animation ticker for fluid edge
    LaunchedEffect(Unit) {
        while (true) {
            withFrameMillis { frameTime ->
                fluidEdge.tick(frameTime)
                fluidAnimationController.update(0.016f, containerSize.toSize())
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .onSizeChanged { containerSize = it }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        // Commit page change from the *previous* drag if it was completed.
                        if (dragIndex != null && dragCompleted) {
                            val childrenSize = children.size
                            currentIndex = ((dragIndex!! % childrenSize) + childrenSize) % childrenSize
                        }

                        // Reset all state for the NEW drag.
                        dragIndex = null
                        dragStartPosition = offset
                        dragCompleted = false
                        dragDirection = 0f

                        // Reset the fluid-edge physics for the new interaction.
                        fluidEdge.farEdgeTension = 0.0
                        fluidEdge.edgeTension = 0.01
                        fluidEdge.reset()
                    },
                    onDrag = { change, _ ->
                        if (dragDirection != 0f && enableBubble) {
                            fluidAnimationController.burst(
                                center = change.position,
                                count = 3, // Adjust count for more/fewer bubbles
                                color = Color.White.copy(alpha = 0.5f) // Adjust color and transparency
                            )
                        }

                        val currentPosition = change.position
                        val dx = currentPosition.x - dragStartPosition.x

                        // Check if the swipe is starting (the threshold of 20px)
                        if (dragDirection == 0f && abs(dx) > 20f) {
                            dragDirection = sign(dx)
                            fluidEdge.side = if (dragDirection == 1f) Side.LEFT else Side.RIGHT
                            // Set the drag index (previous or next card)
                            dragIndex = currentIndex - dragDirection.toInt()
                        }

                        // If swipe is active, update fluid edge
                        if (dragDirection != 0f) {
                            // Adjust dx for right swipe
                            val adjustedDx = if (dragDirection == -1f) {
                                containerSize.width + dx
                            } else {
                                dx
                            }

                            // Apply touch offset to fluid edge
                            fluidEdge.applyTouchOffset(
                                Offset(adjustedDx, currentPosition.y),
                                containerSize.toSize()
                            )

                            // Check if the swipe is complete
                            if (!dragCompleted) {
                                val availableWidth = if (dragDirection == 1f) {
                                    containerSize.width - dragStartPosition.x
                                } else {
                                    dragStartPosition.x
                                }

                                val ratio = abs(dx) / availableWidth

                                // Complete swipe if dragged 80% of available width
                                // and started from at least 50% of screen width
                                if (ratio > 0.8f && availableWidth / containerSize.width > 0.5f) {
                                    dragCompleted = true
                                    fluidEdge.farEdgeTension = 0.01
                                    fluidEdge.edgeTension = 0.0
                                    fluidEdge.applyTouchOffset(null)
                                }
                            }
                        }
                    },
                    onDragEnd = {
                        // on screen and play its physics-based return animation.
                        fluidEdge.applyTouchOffset(null)

                        if (dragCompleted) {
                            // CASE 1: Swipe was successful (dragged far enough).
                            // Let the farEdgeTension (set during onDrag) pull the card fully open.
                            fluidEdge.edgeTension = 0.0
                        } else {
                            // CASE 2: Swipe was aborted (released mid-way).
                            fluidEdge.apply {
                                edgeTension = 0.01
                                farEdgeTension = 0.0
                            }
                        }
                    }
                )
            }
    ) {
        // Base card (current page)
        CompositionLocalProvider(
            LocalCarouselInfo provides CarouselInfo(
                currentIndex = currentIndex,
                allPage = children.size
            )
        ) {
            children.getOrNull(currentIndex % children.size)?.invoke()
        }

        // Dragging card with fluid clip
        dragIndex?.let { index ->
            // Ensure valid index
            val childrenSize = children.size
            val validIndex = ((index % childrenSize) + childrenSize) % childrenSize

            CompositionLocalProvider(
                LocalCarouselInfo provides CarouselInfo(
                    currentIndex = validIndex,
                    allPage = children.size
                )
            ) {
                FluidClipBox(
                    fluidEdge = fluidEdge,
                    modifier = Modifier.fillMaxSize()
                ) {
                    children.getOrNull(validIndex)?.invoke()
                }
            }
        }

        BubbleCanvas(controller = fluidAnimationController)
    }
}

/**
 * FluidClipBox - Clips content with the fluid-edge path
 */
@Composable
fun FluidClipBox(
    fluidEdge: FluidEdge,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val version = fluidEdge.version

    val fluidShape = remember(version) {
        object : Shape {
            override fun createOutline(
                size: Size,
                layoutDirection: LayoutDirection,
                density: Density
            ): Outline {
                // The path is built using the latest point data from fluidEdge.
                return Outline.Generic(
                    fluidEdge.buildPath(size, margin = 10f)
                )
            }
        }
    }

    Box(modifier = modifier.clip(fluidShape)) {
        content()
    }
}

@Composable
private fun BubbleCanvas(controller: FluidAnimationController) {
    // By reading the 'version' state here, subscribe this composable to its changes.
    val version = controller.version

    // This Canvas will now be recomposed (and redrawn) every time 'version' changes.
    key(version) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            controller.draw(this)
        }
    }
}