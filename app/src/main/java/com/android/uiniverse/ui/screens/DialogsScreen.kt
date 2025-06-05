package com.android.uiniverse.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.android.uiniverse.R
import com.android.uiniverse.viewmodel.MainViewModel
import kotlinx.coroutines.launch

val mainViewModel = MainViewModel()


@Composable
fun DialogsContent(activity: Activity) {

    Column(modifier = Modifier, verticalArrangement = Arrangement.SpaceEvenly) {

        Row(
            modifier = Modifier.fillMaxWidth() .padding(top = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PermissionDialogButton(activity)

            SystemAlertDialog()

            AlertDialog()

            CarUiAlertDialog()
        }

        /*Row(
            modifier = Modifier.fillMaxWidth() .padding(top = 40.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ToastButton()

            MaterialSnackbar()
        }*/
    }
}


@Composable
fun PermissionDialogButton(activity: Activity) {
    val context = LocalContext.current

    val buttonWidth = if (isCar(context)) 200.dp else 148.dp
    val buttonHeight = if (isCar(context)) 80.dp else 70.dp


    ExtendedFloatingActionButton(
        text = {
            Text(
                text = stringResource(R.string.permission_dialog),
                color = Color.Black,
                fontSize = 20.sp
            )
        },
        icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "Localized Description") },
        onClick = { mainViewModel.permissionDialog(context, activity) },
        modifier = Modifier
            .padding(start = 40.dp, end = 12.dp),
        shape = FloatingActionButtonDefaults.largeShape
    )
}


@Composable
fun SystemAlertDialog() {
    val context = LocalContext.current
    val buttonWidth = if (isCar(context)) 140.dp else 140.dp
    val buttonHeight = if (isCar(context)) 50.dp else 50.dp

    ElevatedButton(
        onClick = {
            mainViewModel.systemAlertDialog(
                context,
                "com.android.car.settings"
            )
        }, modifier = Modifier
            .padding(end = 12.dp)
            .width(180.dp)
            .height(70.dp)
    ) {
        Text(
            text = stringResource(R.string.system_dialog),
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.basicMarquee()
        )
    }
}

@Composable
fun AlertDialog() {
    val context = LocalContext.current
    val buttonWidth = if (isCar(context)) 160.dp else 128.dp
    val buttonHeight = if (isCar(context)) 80.dp else 50.dp
    FilledTonalButton(
        onClick = { mainViewModel.alertDialog(context) }, modifier = Modifier
            .padding(end = 12.dp)
            .width(180.dp)
            .height(70.dp)
    ) {
        Text(
            text = stringResource(R.string.alert_dialog),
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.basicMarquee()
        )
    }

}

fun showCarUiDialog(context: Context) {
    val activity = context as? FragmentActivity
    activity?.let {
        val dialog = CarUiLibraryDialog()
        val bundle = Bundle().apply {
            putString("someKey", "someValue")
        }
        dialog.arguments = bundle
        dialog.show(it.supportFragmentManager, "CarUiDialog")
    }
}

@Composable
fun CarUiAlertDialog() {
    val context = LocalContext.current
    val buttonWidth = if (isCar(context)) 160.dp else 128.dp
    val buttonHeight = if (isCar(context)) 80.dp else 50.dp
    val isAutomotive = remember { isCar(context) }
    Button(
        onClick = { showCarUiDialog(context) },
        modifier = Modifier
            .padding(end = 12.dp)
            .width(180.dp)
            .height(70.dp)
            .basicMarquee(),
        enabled = isAutomotive,
        shape = ButtonDefaults.elevatedShape,
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(
            text = "Car Dialog",
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.basicMarquee()
        )
    }

    if (!isAutomotive) {
        Snackbar()
    }
}

fun isCar(context: Context): Boolean {
    val manager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    return manager.currentModeType == Configuration.UI_MODE_TYPE_CAR
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun Snackbar() {
    val hostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(hostState) }) {
        scope.launch {
            hostState.showSnackbar(
                "This functionality is only for cars!!"
            )
        }
    }
}

@Preview
@Composable
fun Dialogs() {
    SystemAlertDialog()
}

