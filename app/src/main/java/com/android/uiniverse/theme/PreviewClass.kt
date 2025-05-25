package com.android.uiniverse.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "Dark Theme",
    group = "Ui Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
annotation class DarkUiTheme