package com.android.uiniverse.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.uiniverse.ui.screens.DialogsContent
import com.android.uiniverse.ui.screens.DisplayContent

class AppNavigation {

    companion object {
        internal const val SCREEN1 = "components_screen"
        private const val SCREEN2 = "dialog_buttons_screen"
        //private const val SCREEN3 = "toast_screen"
    }

    @Composable
    fun NavigationController(activity: Activity) {

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = SCREEN1
        ) {
            composable(SCREEN1) {
                DisplayContent() { navController.navigate(route = SCREEN2) }
            }

            composable(SCREEN2) { navBackStackEntry: NavBackStackEntry ->
                DialogsContent(activity)
            }


        }


    }


}




