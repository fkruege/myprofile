package com.franctan.mypassport.ui.main.editprofilefragment

import com.franctan.models.Profile
import com.franctan.utilities.IoScheduler
import com.franctan.utilities.MainScheduler
import com.franctan.utilities.UriHelper
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class ProfileSaver
@Inject constructor(
        private val profileDaoHelper: ProfileDaoHelper,
        private val photoUploadHelper: PhotoUploadHelper,
        private val uriHelper: UriHelper,
        @IoScheduler private val ioScheduler: Scheduler,
        @MainScheduler private val mainScheduler: Scheduler
) {

    fun saveProfile(profile: Profile): Single<Profile> {
        return photoUploadHelper.createUploadPhotoRx(profile)
                .map { uri -> uriHelper.parseUri(uri) }
                .map { uriString -> profile.copy(profilePhotoPath = uriString) }
                .flatMap { saveProfile -> profileDaoHelper.createSaveProfileRx(saveProfile) }
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)

    }


}


