package dev.chayaphon.lib.fluid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Slider indicators component
 */
@Composable
fun SliderIndicators(
    currentIndex: Int,
    allPage: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(allPage) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(8.dp)
                    .background(
                        color = if (index == currentIndex) {
                            color
                        } else {
                            color.copy(alpha = 0.3f)
                        },
                        shape = CircleShape
                    )
            )
        }
    }
}