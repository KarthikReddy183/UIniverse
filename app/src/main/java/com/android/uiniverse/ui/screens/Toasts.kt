package com.android.uiniverse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.uiniverse.R
import kotlinx.coroutines.launch


@Composable
fun ToastButton() {

    val context = LocalContext.current

    OutlinedButton(
        onClick = { mainViewModel.toast(context) }, modifier = Modifier
            .width(180.dp)
            .height(70.dp)
    ) {

        Text(
            text = stringResource(R.string.platform_toast),
            color = Color.White,
            fontSize = 20.sp
        )
    }
}


@Composable
fun MaterialSnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {

        // UI content — doesn't move
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("This functionality is only for cars!!")
                    }
                },
                modifier = Modifier
                    .width(160.dp)
                    .height(50.dp)
            ) {
                Text("Show Snackbar")
            }
        }

        // ✅ Snackbar is overlaid, not affecting layout
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            SnackbarHost(hostState = snackbarHostState)
        }
    }
}
