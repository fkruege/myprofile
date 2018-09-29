package com.franctan.mypassport.ui.main.viewprofilefragment

import android.arch.lifecycle.ViewModel
import com.franctan.firebaserepo.daos.ProfilesDao
import com.franctan.mypassport.ui.models.UIProfileModel
import javax.inject.Inject

class ViewProfileModel
@Inject constructor(private val profilesDao: ProfilesDao) : ViewModel() {

    val uiProfile = UIProfileModel()

}


