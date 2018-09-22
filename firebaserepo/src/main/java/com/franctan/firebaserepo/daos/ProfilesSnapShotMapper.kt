package com.franctan.firebaserepo.daos

import com.franctan.firebaserepo.models.FbProfile
import com.franctan.firebaserepo.models.mapToProfile
import com.franctan.models.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseException
import timber.log.Timber
import javax.inject.Inject


class ProfilesSnapShotMapper
@Inject constructor()
{

    internal fun tryToMapToProfile(snapshot: DataSnapshot): Profile? {
        var profile: Profile? = null
        try {
            snapshot.key?.let { inKey ->
                profile = snapshot.getValue(FbProfile::class.java)?.mapToProfile(inKey)
            }
        } catch (ex: Exception) {
            Timber.e(ex)
            if (!isParsingException(ex)) {
                throw ex
            }
        }

        return profile
    }

    private fun isParsingException(ex: Exception): Boolean {
        return ex is DatabaseException
                || ex is IllegalArgumentException
                || ex is NumberFormatException
    }


}