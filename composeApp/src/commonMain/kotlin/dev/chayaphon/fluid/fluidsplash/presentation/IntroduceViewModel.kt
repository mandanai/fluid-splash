package dev.chayaphon.fluid.fluidsplash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dev.chayaphon.fluid.fluidsplash.model.CardData
import dev.chayaphon.fluid.fluidsplash.model.FluidResources
import dev.chayaphon.lib.fluid.model.Background
import dev.chayaphon.lib.fluid.model.FluidGraphicConfig
import org.jetbrains.compose.resources.painterResource

class IntroduceViewModel(): ViewModel() {

    @Composable
    fun getCardDetail(): List<CardData> {
        return listOf(
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
            ),
            CardData(
                config = FluidGraphicConfig(
                    background = Background.BackgroundPainter(
                        painterResource(FluidResources.Backgrounds.PURPLE)
                    ),
                    illustration = painterResource(FluidResources.Illustrations.PURPLE)
                ),
                altColor = Color(0xFFFFB138),
                title = "Victory on your way,\nresounding your dream,\ncustom your view",
                subtitle = ""
            )
        )
    }
}