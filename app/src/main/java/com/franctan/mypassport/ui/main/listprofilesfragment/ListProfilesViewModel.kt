package com.franctan.mypassport.ui.main.listprofilesfragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.franctan.models.Profile
import com.franctan.mypassport.preferences.RxPreferences
import com.franctan.mypassport.ui.main.filterdialogfragment.FilterSortMapper
import com.franctan.mypassport.ui.viewmodelsupport.SingleLiveEvent
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class ListProfilesViewModel
@Inject constructor(
        private val listProfilesObserver: ListProfilesObserver,
        private val rxPreferences: RxPreferences,
        private val filterSortMapper: FilterSortMapper
) : ViewModel() {

    private val profilesLiveData = MutableLiveData<List<Profile>>()
    private val compositeDisposable = CompositeDisposable()

    internal val addNewProfileEvent = SingleLiveEvent<Void>()


    val ProfilesLiveData: LiveData<List<Profile>>
        get() = profilesLiveData

    init {
        loadProfiles()
    }

    fun addNewProfileClick() {
        addNewProfileEvent.call()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun clearFilterAndSort() {
        val defaultFilter = filterSortMapper.getFilterStringDefault()
        val defaultSort = filterSortMapper.getSortStringDefault()

        rxPreferences.setFilter(defaultFilter)
        rxPreferences.setSort(defaultSort)
    }

    private fun loadProfiles() {

        listProfilesObserver
                .observeProfiles()
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


