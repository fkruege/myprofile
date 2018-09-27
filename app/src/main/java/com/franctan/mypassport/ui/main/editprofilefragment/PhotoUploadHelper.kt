package com.franctan.mypassport.ui.main.editprofilefragment

import android.net.Uri
import com.franctan.firebasecloudstorage.dagger.FileStorageDao
import com.franctan.models.Profile
import com.franctan.utilities.UriHelper
import io.reactivex.Single
import javax.inject.Inject


class PhotoUploadHelper
@Inject constructor(
        private val fileStorageDao: FileStorageDao,
        private val uriHelper: UriHelper
) {


    fun createUploadPhotoRx(profile: Profile): Single<Uri> {
        return if (profile.profilePhotoPath.isEmpty()) {
            Single.just(Uri.EMPTY)
        } else if (uriHelper.isWebPath(profile.profilePhotoPath)) {
            return Single.just(Uri.parse(profile.profilePhotoPath))
        } else {
            fileStorageDao.upload(profile.profilePhotoPath)
        }
    }


}


