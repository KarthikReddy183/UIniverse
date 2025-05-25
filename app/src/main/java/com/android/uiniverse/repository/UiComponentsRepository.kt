package com.android.uiniverse.repository

//noinspection SuspiciousImport
import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import com.android.car.ui.core.CarUi

/*
* Todo
*  Show the list of installed apps
* */

class UiComponentsRepository {


    var intent = Intent()


    /*todo -> crete switch case with enums for different types of dialogs & different permissions as well*/
    fun dialog(context: Context, activity: Activity) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            Toast.makeText(context, "Permission granted", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(
                activity, arrayOf<String>(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 123
            )
        }
    }

    fun toast(context: Context) {
        Toast.makeText(context, "Platform UI toast", Toast.LENGTH_LONG).show()
    }

    fun aaosToastComponent(context: CarContext){
        CarToast.makeText(context,"This is car toast component", CarToast.LENGTH_LONG).show()
    }

    fun appUninstallSystemAlertDialog(context: Context, packageName: String) {

        if (false) {
            Toast.makeText(context, "Package is not present on this device", Toast.LENGTH_LONG)
                .show()
        } else {
            intent = Intent(Intent.ACTION_DELETE)
            intent.setData("package:$packageName".toUri()).toUri(0)
            context.startActivity(intent)
        }

    }

    fun alertDialog(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context, 4)
        alertDialogBuilder.setTitle("Platform UI Alert Dialog")
        alertDialogBuilder.setMessage("Use this alert dialog to test the look and feel of figma dialog")

        alertDialogBuilder.setCancelable(false)
            .setPositiveButton(R.string.ok) { dialog, id ->
                Toast.makeText(context, "Clicked Ok", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(R.string.cancel) { dialog, id -> dialog.dismiss() }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    @SuppressLint("ObsoleteSdkInt")
    fun getDisplayWidth(context: Context): Int {

        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            windowMetrics.bounds.width()
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getRealMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    fun getDisplayHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            windowMetrics.bounds.height()
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getRealMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }


}