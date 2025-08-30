package dev.chayaphon.lib.fluid.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.isActive

@Composable
fun MetaballBackground(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(
        Color(0xFF673AB7),
        Color(0xFF3F51B5),
        Color(0xFF4FC3F7)
    )
) {
    val metaballRenderer = remember {
        MetaballRenderer(
            ballCount = 7,
            colors = colors
        )
    }
    var size by remember { mutableStateOf(IntSize.Zero) }

    // This effect continuously updates the metaball positions.
    LaunchedEffect(metaballRenderer) {
        while (isActive) {
            withFrameMillis {
                // Pass the actual component size to the update function.
                metaballRenderer.update(size.toSize())
            }
        }
    }

    // Use the key() composable to force the Canvas to redraw
    // every time the renderer's version changes.
    key(metaballRenderer.version) {
        Canvas(
            modifier = modifier
                .fillMaxSize()
                .onSizeChanged {
                    // Capture the actual size of the canvas.
                    size = it
                }
        ) {
            metaballRenderer.draw(
                drawScope = this
            )
        }
    }
}