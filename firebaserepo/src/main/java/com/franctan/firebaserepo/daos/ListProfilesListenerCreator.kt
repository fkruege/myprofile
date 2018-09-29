package com.franctan.firebaserepo.daos

import com.franctan.models.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.ObservableEmitter
import timber.log.Timber
import javax.inject.Inject

class ListProfilesListenerCreator
@Inject constructor(
        private val profilesMapper: ProfilesSnapShotMapper
) {


    internal fun createValueListener(emitter: ObservableEmitter<List<Profile>> ): ValueEventListener {
        return object : ValueEventListener {
            override fun onCancelled(dbError: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val profileList = mutableListOf<Profile>()

                Timber.d("new snapshot")
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
                emitter.onNext(profileList)
            }
        }
    }

}


