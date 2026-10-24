package dev.chayaphon.fluid.fluidsplash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import dev.chayaphon.fluid.fluidsplash.model.CardData
import dev.chayaphon.lib.fluid.ui.FluidCardCustom
import dev.chayaphon.lib.fluid.ui.FluidCarousel
import dev.chayaphon.lib.fluid.ui.LocalCarouselInfo
import dev.chayaphon.lib.fluid.ui.SliderIndicators
import org.jetbrains.compose.resources.ExperimentalResourceApi
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun IntroduceScreen() {
    val viewModel = IntroduceViewModel()
    val cards = viewModel.getCardDetail()

    val cardBox = cards.map { card ->
        @Composable {
            FluidCardCustom(
                background = card.config.background,
                content = {
                    FinishCard(
                        card = card
                    )
                }
            )
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        FluidCarousel(
            children = cardBox
        )
    }
}

@Composable
private fun FinishCard(
    card: CardData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 75.dp, bottom = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(3f)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            val illustrationPainter = card.config.illustration
            if (illustrationPainter != null) {
                Image(
                    painter = illustrationPainter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }

        // Slider indicators
        run {
            val info = LocalCarouselInfo.current
            SliderIndicators(
                currentIndex = info?.currentIndex ?: 0,
                allPage = info?.allPage ?: 1,
                color = card.config.indicatorColor,
                modifier = Modifier.height(14.dp)
            )
        }

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
                Text(
                    text = card.title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 36.sp,
                        color = Color.White
                    )
                )

                if (card.subtitle.isNotEmpty()) {
                    Text(
                        text = card.subtitle,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    )
                } else {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .height(48.dp),
                        shape = MaterialTheme.shapes.medium,
                        content = {
                            Text(
                                text = "Learn More",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}