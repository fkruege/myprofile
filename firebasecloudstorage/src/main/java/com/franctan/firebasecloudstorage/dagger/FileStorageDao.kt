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
//
//    abstract inner class Task<TResult> {
//
//        abstract val isComplete: Boolean
//
//        abstract val isSuccessful: Boolean
//
//        abstract val isCanceled: Boolean
//
//        abstract val result: TResult
//
//        @get:Nullable
//        abstract val exception: Exception
//
//        @Throws(X::class)
//        abstract fun <X : Throwable> getResult(@NonNull var1: Class<X>): TResult
//
//        @NonNull
//        abstract fun addOnSuccessListener(@NonNull var1: OnSuccessListener<in TResult>): Task<TResult>
//
//        @NonNull
//        abstract fun addOnSuccessListener(@NonNull var1: Executor, @NonNull var2: OnSuccessListener<in TResult>): Task<TResult>
//
//        @NonNull
//        abstract fun addOnSuccessListener(@NonNull var1: Activity, @NonNull var2: OnSuccessListener<in TResult>): Task<TResult>
//
//        @NonNull
//        abstract fun addOnFailureListener(@NonNull var1: OnFailureListener): Task<TResult>
//
//        @NonNull
//        abstract fun addOnFailureListener(@NonNull var1: Executor, @NonNull var2: OnFailureListener): Task<TResult>
//
//    }


}




