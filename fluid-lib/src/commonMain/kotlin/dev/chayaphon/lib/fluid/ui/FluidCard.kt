package dev.chayaphon.lib.fluid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chayaphon.lib.fluid.model.FluidGraphicConfig
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.time.ExperimentalTime

/**
 * FluidCard - Card with an animated fluid background
 */
@OptIn(ExperimentalResourceApi::class, ExperimentalTime::class)
@Composable
fun FluidCard(
    config: FluidGraphicConfig,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    FluidContainer(
        background = config.background,
        modifier = modifier
    ) {
        // Content overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 75.dp, bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top illustration placeholder
            Box(
                modifier = Modifier
                    .weight(3f)
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                // Try to load illustration image, fallback to emoji
                val illustrationPainter = config.illustration
                if (illustrationPainter != null) {
                    Image(
                        painter = illustrationPainter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    // Fallback emoji
                    Text(
                        text = config.illustrationPlaceholder,
                        fontSize = 80.sp
                    )
                }
            }

            // Slider indicators
            run {
                val info = LocalCarouselInfo.current
                SliderIndicators(
                    currentIndex = info?.currentIndex ?: 0,
                    allPage = info?.allPage ?: 1,
                    color = config.indicatorColor,
                    modifier = Modifier.height(14.dp)
                )
            }

            // Bottom content
            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 18.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Title
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            lineHeight = 36.sp,
                            color = Color.White
                        )
                    )

                    // Subtitle
                    Text(
                        text = subtitle,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    )
                }
            }
        }
    }
}