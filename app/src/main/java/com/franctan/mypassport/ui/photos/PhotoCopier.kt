package com.franctan.mypassport.ui.photos

import android.content.Context
import android.net.Uri
import com.franctan.utilities.UUIDGenerator
import io.reactivex.Single
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class PhotoCopier
@Inject constructor(
        private val context: Context,
        private val uuid: UUIDGenerator
) {

    fun copyPhoto(uri: Uri): Single<String> {

        return Single.fromCallable {
            var outputFilePath = ""
            val inputStream = context.contentResolver.openInputStream(uri)
            if (inputStream != null) {
                val fileName = uuid.generate().toString()
                val outputFile = File(context.cacheDir, fileName)
                val outputStream = FileOutputStream(outputFile)
                copyStream(inputStream, outputStream)

                outputStream.close()
                inputStream.close()
                outputFilePath = outputFile.absolutePath
            }

            outputFilePath
        }

    }

    fun copyStream(input: InputStream, output: OutputStream) {

        val buffer = ByteArray(1024)
        var bytesRead = input.read(buffer)
        while (bytesRead != -1) {
            output.write(buffer, 0, bytesRead)
            bytesRead = input.read(buffer)
        }
    }


}


