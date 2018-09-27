package com.franctan.mypassport.ui.permissions

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View
import com.franctan.mypassport.R
import com.franctan.mypassport.ui.common.SnackBarMsgDisplayer
import io.reactivex.functions.Action
import javax.inject.Inject


class SnackbarPermissionsDisplayer
@Inject constructor(
        private val activity: Activity,
        private val snackBarMsgDisplayer: SnackBarMsgDisplayer
) {

    fun displayRationaleForExternalStorage(okAction: Action) {
        val view = activity.findViewById<View>(R.id.container)
        view?.let { inView ->
            Snackbar.make(inView, R.string.external_storage_access_required, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok) { okAction.run() }
                    .show()
        }
    }

    fun displayUnavailablePermission() {
        val msg = activity.getString(R.string.external_storage_unavailable)
        snackBarMsgDisplayer.displayMsg(msg)
    }

}