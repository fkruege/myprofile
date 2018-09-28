package com.franctan.firebaserepo.daos

import com.franctan.firebaserepo.dagger.ProfilesDatabaseReference
import com.franctan.firebaserepo.models.mapToFireBaseProfile
import com.franctan.firebaserepo.models.mapToUpdateMap
import com.franctan.models.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.*
import timber.log.Timber
import javax.inject.Inject


class ProfilesDao
@Inject constructor(
        @ProfilesDatabaseReference private val dbReference: DatabaseReference,
        private val profilesMapper: ProfilesSnapShotMapper,
        private val listenerCreator: ListProfilesListenerCreator
) {


    fun getProfileListObserver(): Observable<List<Profile>> {
        var listener: ValueEventListener? = null

        return Observable
                .create { emitter: ObservableEmitter<List<Profile>> ->
                    listener = listenerCreator.createValueListener(emitter, profilesMapper)
                    listener?.let { inListener -> dbReference.addValueEventListener(inListener) }
                }
                .doOnDispose {
                    listener?.let { inListener ->
                        Timber.d("Remove Reference")
                        dbReference.removeEventListener(inListener)
                    }
                }
    }


    fun getProfiles(): Single<List<Profile>> {
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
                                    val profile = profilesMapper.tryToMapToProfile(item)
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
        return Single.create { emitter: SingleEmitter<Profile> ->
            val key = profile.id
            val childUpdates = mutableMapOf<String, Any>()
            childUpdates[key] = profile.mapToUpdateMap()

            dbReference.updateChildren(childUpdates)
                    .addOnFailureListener { ex -> emitter.onError(ex) }
                    .addOnSuccessListener { emitter.onSuccess(profile) }

        }
    }

    fun deleteProfile(profile: Profile): Completable {
        return Completable.create { emitter ->
            dbReference.child(profile.id).removeValue()
                    .addOnFailureListener { ex -> emitter.onError(ex) }
                    .addOnSuccessListener { emitter.onComplete() }
        }

    }


}