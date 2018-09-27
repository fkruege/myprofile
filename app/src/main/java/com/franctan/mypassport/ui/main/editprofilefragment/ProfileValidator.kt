package com.franctan.mypassport.ui.main.editprofilefragment

import com.franctan.models.Profile
import com.franctan.utilities.ResultHolder
import javax.inject.Inject


class ProfileValidator
@Inject constructor() {

    fun isSaveValid(profile: Profile): ResultHolder {

        if (profile.firstName.isEmpty()) {
            return ResultHolder(false, "Please enter a first name.")
        }

        if (profile.lastName.isEmpty()) {
            return ResultHolder(false, "Please enter a last name.")
        }

        return ResultHolder(true)

    }

}