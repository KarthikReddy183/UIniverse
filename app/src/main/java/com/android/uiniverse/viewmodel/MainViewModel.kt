package com.android.uiniverse.viewmodel

import android.app.Activity
import android.content.Context
import androidx.car.app.CarContext
import com.android.uiniverse.repository.UiComponentsRepository

class MainViewModel {

    val repository = UiComponentsRepository()

    fun permissionDialog(context: Context, activity: Activity) {
        repository.dialog(context, activity)
    }

    fun toast(context: Context) {
        repository.toast(context)
    }

    fun systemAlertDialog(context: Context, packageName: String) {
        repository.appUninstallSystemAlertDialog(context = context, packageName = packageName)
    }

    fun alertDialog(context: Context) {
        repository.alertDialog(context)
    }

    fun carToast(context: CarContext) {
        repository.aaosToastComponent(context)
    }

    fun screenDisplayWidth() {

    }

    fun screenDisplayHeight() {

    }
}