package com.franctan.firebaserepo.daos

import com.franctan.models.Profile
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import timber.log.Timber
import javax.inject.Inject

class ProfileObserverCreator
@Inject constructor(
        private val profilesMapper: ProfilesSnapShotMapper,
        private val profileListListenerCreator: ListProfilesListenerCreator,
        private val singleProfileListenerCreator: SingleProfileListenerCreator
) {

    internal fun createProfileListObserver(inDbReference: DatabaseReference): Observable<List<Profile>> {
        var listener: ValueEventListener? = null
        return Observable
                .create { emitter: ObservableEmitter<List<Profile>> ->
                    listener = profileListListenerCreator.createValueListener(emitter)
                    listener?.let { inListener -> inDbReference.addValueEventListener(inListener) }
                }
                .doOnDispose {
                    listener?.let { inListener ->
                        Timber.d("Remove Reference")
                        inDbReference.removeEventListener(inListener)
                    }
                }
    }


    internal fun createSingleProfileObserver(inDbReference: DatabaseReference): Observable<Profile> {
        var listener: ValueEventListener? = null
        return Observable
                .create { emitter: ObservableEmitter<Profile> ->
                    listener = singleProfileListenerCreator.createValueListener(emitter)
                    listener?.let { inListener -> inDbReference.addValueEventListener(inListener) }
                }
                .doOnDispose {
                    listener?.let { inListener ->
                        Timber.d("Remove Reference")
                        inDbReference.removeEventListener(inListener)
                    }
                }
    }
//
//
//    internal fun <T> createProfileListObserver(inDbReference: DatabaseReference, inListener: ValueEventListener): Observable<T> {
//        return Observable
//                .create { emitter: ObservableEmitter<T> ->
//                    inDbReference.addValueEventListener(inListener)
//                }
//                .doOnDispose {
//                    Timber.d("Remove Reference")
//                    inDbReference.removeEventListener(inListener)
//                }
//    }

}


