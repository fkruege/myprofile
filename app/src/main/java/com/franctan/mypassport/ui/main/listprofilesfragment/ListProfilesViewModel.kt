package com.franctan.mypassport.ui.main.listprofilesfragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.franctan.firebaserepo.daos.ProfilesDao
import com.franctan.models.Profile
import com.franctan.utilities.IoScheduler
import com.franctan.utilities.MainScheduler
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class ListProfilesViewModel
@Inject constructor(
        private val profilesDao: ProfilesDao,
        @IoScheduler private val ioScheduler: Scheduler,
        @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    private val profilesLiveData = MutableLiveData<List<Profile>>()
    private val compositeDisposable = CompositeDisposable()

    val ProfilesLiveData: LiveData<List<Profile>>
        get() = profilesLiveData

    init {
        loadProfiles()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun loadProfiles() {
//        profilesDao.getProfiles()
        profilesDao.getProfileListObserver()
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(object : Observer<List<Profile>> {
                    override fun onComplete() {
                        Timber.d("onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(list: List<Profile>) {
                        profilesLiveData.value = list
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                    }
                })

    }


}


