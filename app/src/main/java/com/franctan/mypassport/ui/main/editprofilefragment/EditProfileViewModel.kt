package com.franctan.mypassport.ui.main.editprofilefragment

import android.arch.lifecycle.ViewModel
import com.franctan.firebaserepo.daos.ProfilesDao
import com.franctan.mypassport.ui.models.UIProfileModel
import javax.inject.Inject


class EditProfileViewModel
@Inject constructor(private val profilesDao: ProfilesDao) : ViewModel() {

    val uiProfile = UIProfileModel()


}