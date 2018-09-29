package com.franctan.mypassport.ui.main.viewprofilefragment

import android.arch.lifecycle.ViewModel
import com.franctan.firebaserepo.daos.IndividualProfileDao
import com.franctan.models.Profile
import com.franctan.mypassport.ui.models.UIProfileModel
import com.franctan.mypassport.ui.viewmodelsupport.SingleLiveEvent
import com.franctan.utilities.IoScheduler
import com.franctan.utilities.MainScheduler
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class ViewProfileViewModel
@Inject constructor(
        private val individualProfileDao: IndividualProfileDao,
        @IoScheduler private val ioScheduler: Scheduler,
        @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    val uiProfile = UIProfileModel()


    internal val editProfileEvent = SingleLiveEvent<String>()

    private val compositeDisposable = CompositeDisposable()

    init {
        val emptyProfile = Profile.EMPTY()
        updateModels(emptyProfile)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun loadUser(profileId: String) {
        individualProfileDao.getOneProfileObserver(profileId)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(object : Observer<Profile> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(profile: Profile) {
                        updateModels(profile)
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                    }
                })
    }

    fun editProfileClick() {
        editProfileEvent.value = uiProfile.profileId
    }

    private fun updateModels(profile: Profile) {
        uiProfile.update(profile)
    }

}


