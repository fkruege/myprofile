package com.franctan.mypassport.ui.main.editprofilefragment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.franctan.firebaserepo.daos.ProfilesDao
import com.franctan.models.Profile
import javax.inject.Inject


class EditProfileViewModel
@Inject constructor(private val profilesDao: ProfilesDao) : ViewModel() {

    private val profileLiveData: MutableLiveData<Profile> = MutableLiveData()

    var firs

    fun setProfileId(id: String) {

        if (id.isNotEmpty()) {

        }

    }

}