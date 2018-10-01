package com.franctan.mypassport.ui.main.editprofilefragment

import com.franctan.models.Profile
import com.franctan.mypassport.R
import com.franctan.mypassport.ui.common.ContextHelper
import com.franctan.utilities.ResultHolder
import javax.inject.Inject


class ProfileValidator
@Inject constructor(
        private val contextHelper: ContextHelper
) {

    fun isSaveValid(profile: Profile): ResultHolder {

        if (profile.firstName.isEmpty()) {
            return ResultHolder(false, contextHelper.getString(R.string.error_enter_first_name))
        }

        if (profile.lastName.isEmpty()) {
            return ResultHolder(false, contextHelper.getString(R.string.error_enter_last_name))
        }

        return ResultHolder(true)

    }

}