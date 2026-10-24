package dev.chayaphon.fluid.fluidsplash.presentation.cool

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import dev.chayaphon.lib.fluid.ui.AnimatedFluidBackground

private val PrimaryFluidColor = Color(0xFF673AB7)
private val SecondaryFluidColor = Color(0xFFFFB727)

@Composable
fun CoolScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedFluidBackground(
            modifier = Modifier.fillMaxSize(),
            primaryColor = PrimaryFluidColor,
            secondaryColor = SecondaryFluidColor
        )

        Text(
            text = "Hello, Fluid World!",
            fontSize = 32.sp,
            color = Color.White
        )
    }
}
