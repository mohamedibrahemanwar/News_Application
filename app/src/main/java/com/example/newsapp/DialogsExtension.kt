package com.example.newsapp

import android.app.AlertDialog
import android.content.DialogInterface
import com.example.newsapp.ui.home.news.NewsFragment

fun NewsFragment.showMessage(
    message: String,
    posActionName: String? = null,
    posAction: DialogInterface.OnClickListener,
    negActionName: String? = null,
    negAction: DialogInterface.OnClickListener
): AlertDialog
 {
    val dialogBuilder= AlertDialog.Builder(context)
     dialogBuilder.setMessage(message)
    if (posActionName != null) {
        dialogBuilder.setPositiveButton(posActionName, posAction)
    }
    if (negActionName != null) {
        dialogBuilder.setNegativeButton(negActionName, negAction)
    }
    return dialogBuilder.show()
}