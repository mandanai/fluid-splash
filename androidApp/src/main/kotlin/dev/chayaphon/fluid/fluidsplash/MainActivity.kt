package dev.chayaphon.fluid.fluidsplash

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val transparentColorInt = Color.TRANSPARENT
        val systemIsDarkTheme = (resources.configuration.uiMode and
                android.content.res.Configuration.UI_MODE_NIGHT_MASK) ==
                android.content.res.Configuration.UI_MODE_NIGHT_YES

        enableEdgeToEdge(
            statusBarStyle = if (systemIsDarkTheme) {
                SystemBarStyle.dark(transparentColorInt)
            } else {
                SystemBarStyle.light(transparentColorInt, transparentColorInt)
            },
            navigationBarStyle = if (systemIsDarkTheme) {
                SystemBarStyle.dark(transparentColorInt)
            } else {
                SystemBarStyle.light(transparentColorInt, transparentColorInt)
            }
        )

        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}