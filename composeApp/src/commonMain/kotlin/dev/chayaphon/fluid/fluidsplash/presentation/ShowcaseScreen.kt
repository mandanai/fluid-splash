package dev.chayaphon.fluid.fluidsplash.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.chayaphon.fluid.fluidsplash.model.CardData
import dev.chayaphon.fluid.fluidsplash.model.FluidResources
import dev.chayaphon.lib.fluid.model.Background
import dev.chayaphon.lib.fluid.model.FluidGraphicConfig
import dev.chayaphon.lib.fluid.ui.FluidCard
import dev.chayaphon.lib.fluid.ui.FluidCarousel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ShowcaseScreen() {
    // Define carousel cards
    val cards = listOf(
        CardData(
            config = FluidGraphicConfig(
                background = Background.BackgroundPainter(
                    painterResource(FluidResources.Backgrounds.RED)
                ),
                illustration = painterResource(FluidResources.Illustrations.RED)
            ),
            altColor = Color(0xFF4259B2),
            title = "Breathe Life\ninto Calm Beginnings",
            subtitle = "Rise refreshed to gentle sounds of nature that softly guide your morning awakening."
        ),
        CardData(
            config = FluidGraphicConfig(
                background = Background.BackgroundPainter(
                    painterResource(FluidResources.Backgrounds.YELLOW)
                ),
                illustration = painterResource(FluidResources.Illustrations.YELLOW)
            ),
            altColor = Color(0xFF904E93),
            title = "Let Breath Become\nYour Mind's Gentle Reset",
            subtitle = "Achieve stress reduction and cognitive enhancement through peer-reviewed breathing protocols—continuously available."
        ),
        CardData(
            config = FluidGraphicConfig(
                background = Background.BackgroundPainter(
                    painterResource(FluidResources.Backgrounds.BLUE)
                ),
                illustration = painterResource(FluidResources.Illustrations.BLUE)
            ),
            altColor = Color(0xFFFFB138),
            title = "Drift on Waves of Gentle\nNarratives into Peaceful Rest",
            subtitle = "Cultivate restorative sleep through therapeutic storytelling and guided mindfulness."
        )
    )

    val cardBox = cards.map { card ->
        @Composable {
            FluidCard(
                config = card.config,
                title = card.title,
                subtitle = card.subtitle
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