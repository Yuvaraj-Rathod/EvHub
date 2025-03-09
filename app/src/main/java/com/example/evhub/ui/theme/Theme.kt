package com.example.evhub.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val EvHubDarkColorScheme = darkColorScheme(
    primary = FuturisticGreen,
    onPrimary = White,
    secondary = DarkBlue,
    onSecondary = LightGray,
    background = MidnightBlue,
    onBackground = White,
    surface = DeepNavyBlue,
    onSurface = LightGray,

)

@Composable
fun EvHubTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()

    if (!view.isInEditMode) {
        SideEffect {
            // Use a slightly lighter dark blue for the top bar so it stands out
            systemUiController.setStatusBarColor(color = MidnightBlue)
            // Use midnight blue for the navigation bar to match the overall background
            systemUiController.setNavigationBarColor(color = MidnightBlue)
        }
    }
    MaterialTheme(
        colorScheme = EvHubDarkColorScheme,
        typography = Typography,
        content = content
    )
}