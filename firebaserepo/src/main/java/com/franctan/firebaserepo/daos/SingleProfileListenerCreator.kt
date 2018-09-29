package com.franctan.firebaserepo.daos

import com.franctan.models.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.ObservableEmitter
import javax.inject.Inject

class SingleProfileListenerCreator
@Inject constructor(
        private val profilesSnapShotMapper: ProfilesSnapShotMapper
) {


    internal fun createValueListener(emitter: ObservableEmitter<Profile>): ValueEventListener {
        return object : ValueEventListener {
            override fun onCancelled(dbError: DatabaseError) {
                emitter.onError(dbError.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val profile = profilesSnapShotMapper.tryToParseProfileFromMap(snapshot)
                    emitter.onNext(profile)
                } catch (ex: Exception) {
                    emitter.onError(ex)
                    return
                }
            }
        }
    }

}


