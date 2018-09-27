package com.franctan.mypassport.ui.main.editprofilefragment

import com.franctan.firebaserepo.daos.ProfilesDao
import com.franctan.models.Profile
import io.reactivex.Single
import javax.inject.Inject

class ProfileDaoHelper
@Inject constructor(
        private val profilesDao: ProfilesDao

) {

    fun createSaveProfileRx(profile: Profile): Single<Profile> {
        if (profile.id.isEmpty()) {
            return profilesDao.saveProfile(profile)
        } else {
            return profilesDao.updateProfile(profile)
        }
    }

}


