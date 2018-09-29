package com.franctan.firebaserepo.daos

import com.franctan.firebaserepo.dagger.ProfilesDatabaseReference
import com.franctan.models.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import javax.inject.Inject

class ProfilesListDao
@Inject constructor(
        @ProfilesDatabaseReference private val dbReference: DatabaseReference,
        private val profileObserverCreator: ProfileObserverCreator,
        private val profilesMapper: ProfilesSnapShotMapper

) {


    fun getProfileListObserver(): Observable<List<Profile>> {
        val localDbReference = dbReference
        return profileObserverCreator.createProfileListObserver(localDbReference)
    }

    fun getProfilesListSingle(): Single<List<Profile>> {
        return Single.create { emitter: SingleEmitter<List<Profile>> ->
            dbReference
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                            emitter.onError(error.toException())
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val profileList = mutableListOf<Profile>()

                            for (item in snapshot.children) {
                                try {
                                    val profile = profilesMapper.tryToParseProfileFromValue(item)
                                    if (profile != null) {
                                        profileList.add(profile)
                                    }
                                } catch (ex: Exception) {
                                    emitter.onError(ex)
                                    return
                                }
                            }
                            emitter.onSuccess(profileList)
                        }
                    })
        }
    }



}


