package dev.chayaphon.lib.fluid.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Path
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * Side enum for fluid edge direction
 */
enum class Side {
    LEFT, TOP, RIGHT, BOTTOM
}

/**
 * FluidEdge - Manages physics simulation for fluid-edge animation
 */
class FluidEdge(
    count: Int = 10,
    var side: Side = Side.LEFT
) {
    private val points = mutableListOf<FluidPoint>()
    var edgeTension = 0.01
    var farEdgeTension = 0.0
    var touchTension = 0.1
    var pointTension = 0.25
    var damping = 0.9
    var maxTouchDistance = 0.15
    private var lastT = 0L

    private var touchOffset: Offset? = null
    var version by mutableStateOf(0) // For triggering recomposition

    init {
        // Initialize points along the edge
        for (i in 0 until count) {
            points.add(FluidPoint(0.0, i.toDouble() / (count - 1)))
        }
    }

    /**
     * Reset all points to the initial position
     */
    fun reset() {
        points.forEach { pt ->
            pt.x = 0.0
            pt.velX = 0.0
            pt.velY = 0.0
        }
        version++
    }

    /**
     * Apply touch offset for interaction
     */
    fun applyTouchOffset(offset: Offset? = null, size: Size = Size.Zero) {
        if (offset == null) {
            touchOffset = null
            return
        }

        // Convert to fractional coordinates
        val fraction = Offset(
            offset.x / size.width,
            offset.y / size.height
        )

        // Apply side-specific transformation
        touchOffset = when (side) {
            Side.LEFT -> fraction
            Side.RIGHT -> Offset(1.0f - fraction.x, 1.0f - fraction.y)
            Side.TOP -> Offset(fraction.y, 1.0f - fraction.x)
            Side.BOTTOM -> Offset(1.0f - fraction.y, fraction.x)
        }
    }

    /**
     * Build path for fluid edge
     */
    fun buildPath(size: Size, margin: Float = 0f): Path {
        if (points.isEmpty()) {
            return Path()
        }

        val mtx = getTransform(size, margin)
        val path = Path()
        val l = points.size

        // Start from a corner
        var pt = FluidPoint(-margin.toDouble(), 1.0).toOffset(mtx)
        path.moveTo(pt.x, pt.y)

        pt = FluidPoint(-margin.toDouble(), 0.0).toOffset(mtx)
        path.lineTo(pt.x, pt.y)

        // Draw the first point
        pt = points[0].toOffset(mtx)
        path.lineTo(pt.x, pt.y)

        // Draw a smooth curve through points
        if (l > 1) {
            var pt1 = points[1].toOffset(mtx)
            path.lineTo(
                pt.x + (pt1.x - pt.x) / 2,
                pt.y + (pt1.y - pt.y) / 2
            )
            for (i in 2 until l) {
                pt = pt1
                pt1 = points[i].toOffset(mtx)
                val midX = pt.x + (pt1.x - pt.x) / 2
                val midY = pt.y + (pt1.y - pt.y) / 2
                path.quadraticTo(pt.x, pt.y, midX, midY)
            }

            path.lineTo(pt1.x, pt1.y)
        }

        // Close path
        path.close()

        return path
    }

    /**
     * Update physics simulation
     */
    fun tick(frameTimeMillis: Long) {
        if (points.isEmpty()) return

        // Calculate time delta
        val currentMillis = frameTimeMillis
        val deltaMs = if (lastT == 0L) 16 else (currentMillis - lastT).toInt()
        lastT = currentMillis

        // Convert to normalized time and cap at 1.5
        val t = min(1.5, deltaMs / 1000.0 * 60.0)
        val dampingT = damping.pow(t)

        val l = points.size

        // Update velocities
        for (i in 0 until l) {
            val pt = points[i]

            // Edge tension forces
            pt.velX -= pt.x * edgeTension * t
            pt.velX += (1.0 - pt.x) * farEdgeTension * t

            // Touch interaction force
            touchOffset?.let { touch ->
                val ratio = max(0.0, 1.0 - abs(pt.y - touch.y) / maxTouchDistance)
                pt.velX += (touch.x - pt.x) * touchTension * ratio * t
            }

            // Point-to-point tension
            if (i > 0) {
                addPointTension(pt, points[i - 1].x, t)
            }
            if (i < l - 1) {
                addPointTension(pt, points[i + 1].x, t)
            }

            // Apply damping
            pt.velX *= dampingT
        }

        // Update positions
        for (i in 0 until l) {
            val pt = points[i]
            pt.x += pt.velX * t
        }

        version++
    }

    /**
     * Add tension force between points
     */
    private fun addPointTension(pt0: FluidPoint, x: Double, t: Double) {
        pt0.velX += (x - pt0.x) * pointTension * t
    }

    /**
     * Get transformation matrix based onside
     */
    private fun getTransform(size: Size, margin: Float): Matrix {
        val vertical = side == Side.TOP || side == Side.BOTTOM
        val w = (if (vertical) size.height else size.width) + margin * 2
        val h = (if (vertical) size.width else size.height) + margin * 2

        val mtx = Matrix()
        mtx.translate(-margin, 0f)
        mtx.scale(w, h)

        when (side) {
            Side.TOP -> {
                mtx.rotateZ(90f) // PI/2 radians
                mtx.translate(0f, -1f)
            }
            Side.RIGHT -> {
                mtx.rotateZ(180f) // PI radians
                mtx.translate(-1f, -1f)
            }
            Side.BOTTOM -> {
                mtx.rotateZ(270f) // 3*PI/2 radians
                mtx.translate(-1f, 0f)
            }
            Side.LEFT -> {
                // Default orientation - no additional transformation
            }
        }

        return mtx
    }

    /**
     * Animate edge back to rest position
     */
    suspend fun animateToRest() {
        // Gradually reduce displacement over 20 frames
        repeat(20) {
            points.forEach { pt ->
                pt.x *= 0.9
                pt.velX *= 0.8
            }
            version++
            delay(16) // ~60 FPS
        }
        reset()
    }
}

/**
 * FluidPoint - Represents a point on the fluid edge
 */
data class FluidPoint(
    var x: Double = 0.0,
    var y: Double = 0.0,
    var velX: Double = 0.0,
    var velY: Double = 0.0
) {
    /**
     * Convert to screen offset with optional transformation
     * Matches Flutter's MatrixUtils.transformPoint
     */
    fun toOffset(transform: Matrix? = null): Offset {
        val offset = Offset(x.toFloat(), y.toFloat())
        return transform?.map(offset) ?: offset
    }
}