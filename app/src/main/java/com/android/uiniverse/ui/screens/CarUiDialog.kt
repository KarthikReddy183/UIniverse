package com.android.uiniverse.ui.screens

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import com.android.car.ui.AlertDialogBuilder
import com.android.car.ui.preference.CarUiDialogFragment

class CarUiLibraryDialog : CarUiDialogFragment() {


    override fun onCreateDialog(bundle: Bundle?): Dialog {

        val dialogBuilder = AlertDialogBuilder(requireContext(), 4)
        dialogBuilder.setTitle("Header Content")
        dialogBuilder.setMessage("This is car ui alert dialog message content")
        dialogBuilder.setNegativeButton("CANCEL") { dialogData, id -> dialog?.dismiss() }
        dialogBuilder.setPositiveButton("OK") { data, id ->
            Toast.makeText(
                context, "Close dialog",
                Toast.LENGTH_SHORT
            ).show()
        }
        return dialogBuilder.create()

    }


    override fun onDialogClosed(closed: Boolean) {

    }
}