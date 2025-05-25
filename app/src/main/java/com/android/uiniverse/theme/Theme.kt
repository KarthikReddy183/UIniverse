package com.android.uiniverse.theme

import android.annotation.SuppressLint
import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.platform.LocalContext
import com.android.uiniverse.theme.LightColorScheme

private val DarkColorScheme = darkColorScheme(
    surface = Blue,
    onSurface = Navy,
    primary = Navy,
    onPrimary = Chartreuse
)

private val LightColorScheme = lightColorScheme(
    surface = Blue,
    onSurface = Color.White,
    primary = LightBlue,
    onPrimary = Navy

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val CarDarkTheme = lightColorScheme(

    surface = Purple80,
    onSurface = Color.White,
    primary = Chartreuse,
    onPrimary = Pink80
)

@SuppressLint("ObsoleteSdkInt")
@Composable
fun UIniverseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isCar(context)) {
                CarDarkTheme
            } else if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

fun isCar(context: Context): Boolean {
    val manager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    return manager.currentModeType == Configuration.UI_MODE_TYPE_CAR
}