package com.franctan.firebasecloudstorage.dagger

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class FirebaseCloudStorageModule {
    companion object {
        val PROFILE_IMAGES = "profile_images"
        val RETRY_LIMIT_MS = 2000L
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        val instance = FirebaseStorage.getInstance()
        instance.maxUploadRetryTimeMillis = RETRY_LIMIT_MS
        instance.maxOperationRetryTimeMillis = RETRY_LIMIT_MS
        return instance
    }

    @Provides
    fun provideStorageReference(firebaseStorage: FirebaseStorage): StorageReference {
        val reference = firebaseStorage.reference
        return reference.child(PROFILE_IMAGES)
    }

}