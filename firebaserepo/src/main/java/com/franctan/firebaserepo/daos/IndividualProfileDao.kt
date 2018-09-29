package com.franctan.firebaserepo.daos

import com.franctan.firebaserepo.dagger.ProfilesDatabaseReference
import com.franctan.firebaserepo.models.mapToFirebaseProfile
import com.franctan.firebaserepo.models.mapToProfile
import com.franctan.models.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import timber.log.Timber
import javax.inject.Inject


class IndividualProfileDao
@Inject constructor(
        @ProfilesDatabaseReference private val dbReference: DatabaseReference,
        private val profileObserverCreator: ProfileObserverCreator,
        private val profilesSnapShotMapper: ProfilesSnapShotMapper
) {


    fun getOneProfileObserver(inId: String): Observable<Profile> {
        val localDbReference = dbReference.child(inId)
        return profileObserverCreator
                .createSingleProfileObserver(localDbReference)
                .map { inProfile -> inProfile.copy(id = inId) }
    }


    fun getProfileSingle(id: String): Single<Profile> {
        return Single.create { emitter: SingleEmitter<Profile> ->
            dbReference.child(id)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                            emitter.onError(error.toException())
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            try {
                                val profile = profilesSnapShotMapper.tryToParseProfileFromMap(snapshot)
                                emitter.onSuccess(profile)
                            } catch (ex: Exception) {
                                emitter.onError(ex)
                                return
                            }
                        }
                    })
        }
    }


}