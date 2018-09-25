package com.franctan.mypassport.ui.photos

import android.content.Context
import android.net.Uri
import com.franctan.mypassport.ui.common.UUIDGenerator
import io.reactivex.Single
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.Callable
import javax.inject.Inject

class PhotoCopier
@Inject constructor(
        private val context: Context,
        private val uuid: UUIDGenerator
) {

    fun copyPhoto(uri: Uri): Single<String> {

        return Single.fromCallable(Callable<String> {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileName = uuid.generate().toString()
            val outputFile = File(context.cacheDir, fileName)
            val outputStream = FileOutputStream(outputFile)
            copyStream(inputStream, outputStream)

            outputStream.close()
            inputStream.close()
            outputFile.absolutePath
        })

    }

    //    @Throws(IOException::class)
    fun copyStream(input: InputStream, output: OutputStream) {

        val buffer = ByteArray(1024)
        var bytesRead = input.read(buffer)
        while (bytesRead != -1) {
            output.write(buffer, 0, bytesRead)
            bytesRead = input.read(buffer)
        }
    }


}


