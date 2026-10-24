package dev.chayaphon.fluid.fluidsplash.presentation.introduce.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import dev.chayaphon.fluid.fluidsplash.presentation.introduce.OnboardingPageUi
import dev.chayaphon.lib.fluid.model.Background
import dev.chayaphon.lib.fluid.ui.FluidCardCustom
import dev.chayaphon.lib.fluid.ui.LocalCarouselInfo
import dev.chayaphon.lib.fluid.ui.SliderIndicators
import org.jetbrains.compose.resources.painterResource

/** A single carousel page: fluid background, illustration, page indicators and text. */
@Composable
fun OnboardingCard(
    page: OnboardingPageUi,
    onCallToActionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FluidCardCustom(
        modifier = modifier,
        background = Background.BackgroundPainter(painterResource(page.background)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 75.dp, bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Illustration(page)
            PageIndicators()
            TextContent(page, onCallToActionClick)
        }
    }
}

@Composable
private fun ColumnScope.Illustration(page: OnboardingPageUi) {
    Box(
        modifier = Modifier
            .weight(3f)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(page.illustration),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun PageIndicators() {
    val info = LocalCarouselInfo.current
    SliderIndicators(
        currentIndex = info?.currentIndex ?: 0,
        allPage = info?.allPage ?: 1,
        color = Color.White,
        modifier = Modifier.height(14.dp)
    )
}

@Composable
private fun ColumnScope.TextContent(
    page: OnboardingPageUi,
    onCallToActionClick: () -> Unit,
) {
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
                text = page.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    lineHeight = 36.sp,
                    color = Color.White
                )
            )

            page.subtitle?.let { subtitle ->
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

            page.callToAction?.let { label ->
                Button(
                    onClick = onCallToActionClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}
