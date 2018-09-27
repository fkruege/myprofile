package com.franctan.utilities

import android.net.Uri
import java.io.File
import javax.inject.Inject

class UriHelper
@Inject constructor() {

    companion object {
        val HTTPS = "https"
    }

    fun getUriFromFilePath(filePath: String): Uri {
        return Uri.fromFile(File(filePath))
    }


    fun parseUri(uri: Uri): String {
        if (uri == Uri.EMPTY) {
            return ""
        } else {
            return uri.toString()
        }
    }

    fun isWebPath(uriString: String): Boolean {
        return uriString.isEmpty() ||
                Uri.parse(uriString)?.scheme?.contains(HTTPS, true) ?: false
    }

}


