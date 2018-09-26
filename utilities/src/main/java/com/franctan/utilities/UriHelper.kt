package com.franctan.utilities

import android.net.Uri
import java.io.File
import javax.inject.Inject

class UriHelper
@Inject constructor() {

    fun getUriFromFilePath(filePath: String): Uri {
        return Uri.fromFile(File(filePath))
    }

}


