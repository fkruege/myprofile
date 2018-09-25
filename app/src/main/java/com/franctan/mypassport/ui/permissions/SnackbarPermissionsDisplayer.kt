package com.franctan.mypassport.ui.permissions

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import com.franctan.mypassport.R
import io.reactivex.functions.Action
import javax.inject.Inject


class SnackbarPermissionsDisplayer
@Inject constructor(private val fragment: Fragment) {

    fun displayRationaleForExternalStorage(okAction: Action) {
        val view = fragment.view
        view?.let { inView ->
            Snackbar.make(inView, R.string.external_storage_access_required, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok) { okAction.run() }
                    .show()
        }
    }

    fun displayUnavailablePermission() {
        val view = fragment.view
        view?.let { inView ->
            Snackbar.make(inView, R.string.external_storage_unavailable, Snackbar.LENGTH_LONG)
                    .show()
        }
    }

}