package com.nguyen.dotloading

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.util.Log

public object DotLoadingDialog {

    var builder: AlertDialog.Builder? = null

    var dialog: Dialog? = null

    fun show(context: Context) {
        val dotLoadingView = DotLoadingView(context)
        builder = AlertDialog.Builder(context, R.style.myFullscreenAlertDialogStyle)
        builder!!.setView(dotLoadingView)
        dialog = builder!!.create()
        dialog!!.show()
    }

    fun dimiss() {
        if(dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            builder = null
        }
    }

}