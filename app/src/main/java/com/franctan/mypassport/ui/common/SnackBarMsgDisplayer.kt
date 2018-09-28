package com.franctan.mypassport.ui.common

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.franctan.mypassport.R
import javax.inject.Inject


class SnackBarMsgDisplayer
@Inject constructor(private val activity: AppCompatActivity) {

    fun displayMsg(msg: String) {
        val view = activity.findViewById<View>(R.id.container)
        view?.let { inView ->
            Snackbar.make(inView, msg, Snackbar.LENGTH_LONG)
                    .show()
        }
    }

}