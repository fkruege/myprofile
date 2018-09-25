package com.franctan.mypassport.ui.photos

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.support.v4.app.Fragment
import com.franctan.mypassport.R
import com.franctan.mypassport.ui.common.ContextHelper
import javax.inject.Inject

class PhotoChooser
@Inject constructor(
        private val fragment: Fragment,
        private val contextHelper: ContextHelper

) {

    companion object {
        val PICK_IMAGE_REQUEST = 100
        val IMAGE = "image/*"
    }

    fun launchChoosePhotoIntent() {
        val intent = Intent()
        intent.type = IMAGE
        intent.action = Intent.ACTION_GET_CONTENT
        fragment.startActivityForResult(Intent.createChooser(intent, contextHelper.getString(R.string.select_photo)), PICK_IMAGE_REQUEST)
    }

    fun isValidActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST && data != null && data.data != null
    }


}


