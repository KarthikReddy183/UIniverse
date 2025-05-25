package com.android.uiniverse.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.android.uiniverse.navigation.AppNavigation
import com.android.uiniverse.theme.UIniverseTheme
import com.android.uiniverse.ui.screens.DisplayContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
* Todo
*  1. Need to adapt the app for different screen sizes
*  2. Add navigation to go to next screen
*  3. Add single instance of viewmodel using factory pattern
*  4. UI styles and themes
*  5. After basic UI components are done go for material UI
*
* Todo Create separate page to launch all the settings app intents i.e. every section which is not using 3rd party app.
*
* */


class MainActivity : AppCompatActivity() {

    val appNav = AppNavigation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { true }

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            splashScreen.setKeepOnScreenCondition { false }
        }

        setContent {
            UIniverseTheme {

                appNav.NavigationController(this@MainActivity)
            }
        }
    }

    @Preview
    @Composable
    fun ComponentsScreenPreview() {
        DisplayContent { }
    }

    @Preview
    @Composable
    fun DialogScreenPreview() {
        TopAppBar()
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
    @Composable
    fun TopAppBar() {
        val nav = rememberNavController()

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "Platform UI",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black
                        )
                    },
                    navigationIcon = {

                        IconButton(onClick = { nav.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint = Color.Black
                            )
                        }

                    },
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
        ) {
            appNav.NavigationController(this@MainActivity)
        }
    }

}