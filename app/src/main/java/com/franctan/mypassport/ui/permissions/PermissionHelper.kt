package com.franctan.mypassport.ui.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import io.reactivex.functions.Action
import javax.inject.Inject

class PermissionHelper
@Inject constructor(
        private val context: Context,
        private val fragment: Fragment,
        private val snackbarPermissions: SnackbarPermissionsDisplayer
) {
    companion object {
        val READ_EXTERNAL_STORAGE_REQUEST_CODE = 200
    }

    fun isGrantedReadStoragePermission(): Boolean {
        return isGrantedPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    fun askForStoragePermission() {
        if (fragment.shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            snackbarPermissions.displayRationaleForExternalStorage(Action { requestStoragePermission() })
        } else {
            snackbarPermissions.displayUnavailablePermission()
            requestStoragePermission()
        }
    }

    fun isExternalStoragePermissionGranted(requestCode: Int, permissions: Array<String>, grantResults: IntArray): Boolean {
        return requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE &&
                grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    private fun requestStoragePermission() {
        fragment.requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_REQUEST_CODE)
    }

    private fun isGrantedPermission(context: Context, permission: String): Boolean {
        return (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED)
    }
}

