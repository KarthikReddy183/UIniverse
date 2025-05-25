package com.android.uiniverse.repository

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.android.uiniverse.repository.WindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun RememberWindowInfo(): WindowInfo {

    val config = LocalConfiguration.current
    return WindowInfo(
        screenWidthInfo = when {
            config.screenWidthDp < 600 -> WindowInfo.WindowType.Compact
            config.screenWidthDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenHeightInfo = when {
            config.screenWidthDp < 480 -> WindowInfo.WindowType.Compact
            config.screenWidthDp < 900 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        width = config.screenWidthDp.dp,
        height = config.screenHeightDp.dp

    )

}


data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val width: Dp,
    val height: Dp
) {
    sealed class WindowType {
        object Compact : WindowType()
        object Medium : WindowType()
        object Expanded : WindowType()
    }
}