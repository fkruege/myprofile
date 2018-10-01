package com.franctan.firebasecloudstorage.dagger

import android.net.Uri
import com.franctan.utilities.UriHelper
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.StorageReference
import io.reactivex.Single
import io.reactivex.SingleEmitter
import javax.inject.Inject


class FileStorageDao
@Inject constructor(
        private val storageReference: StorageReference,
        private val uriHelper: UriHelper) {

    fun upload(filePath: String): Single<Uri> {

        return Single.create { emitter: SingleEmitter<Uri> ->

            val fileUri = uriHelper.getUriFromFilePath(filePath)
            val newFileReference = storageReference.child(fileUri.lastPathSegment!!)
            val uploadTask = newFileReference.putFile(fileUri)

            uploadTask
                    .continueWithTask { task ->
                        if (task.isSuccessful) {
                            newFileReference.downloadUrl
                        } else {
                            Tasks.forException<Uri>(getExceptionFromTask(task))
                        }
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val downloadUri = task.result
                            emitter.onSuccess(downloadUri)
                        } else {
                            emitter.onError(getExceptionFromTask(task))
                        }
                    }
        }


    }


    private fun <T> getExceptionFromTask(task: Task<T>): Exception {
        return task.exception?.let { it } ?: IllegalStateException("Could not upload file")


    }
}




