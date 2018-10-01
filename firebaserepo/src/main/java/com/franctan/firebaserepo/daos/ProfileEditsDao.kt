package com.franctan.firebaserepo.daos

import com.franctan.firebaserepo.dagger.ProfilesDatabaseReference
import com.franctan.firebaserepo.models.mapToFireBaseProfile
import com.franctan.firebaserepo.models.mapToUpdateMap
import com.franctan.models.Profile
import com.google.firebase.database.DatabaseReference
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ProfileEditsDao
@Inject constructor(
        @ProfilesDatabaseReference private val dbReference: DatabaseReference
) {


    fun saveProfile(profile: Profile): Single<Profile> {
        return Single.fromCallable {
            val fbProfile = profile.mapToFireBaseProfile()

            val key = dbReference.push().key
            if (key != null) {
                dbReference.child(key).setValue(fbProfile)
                profile.copy(id = key)
            } else {
                throw IllegalStateException("Could not save record")
            }

        }
    }

    fun updateProfile(profile: Profile): Single<Profile> {
        return Single.fromCallable {
            val key = profile.id
            val childUpdates = mutableMapOf<String, Any>()
            childUpdates[key] = profile.mapToUpdateMap()

            dbReference.updateChildren(childUpdates)
            profile
        }
    }

    fun deleteProfile(profile: Profile): Completable {
        return Completable.fromAction {
            dbReference.child(profile.id).removeValue()
        }
    }

}


