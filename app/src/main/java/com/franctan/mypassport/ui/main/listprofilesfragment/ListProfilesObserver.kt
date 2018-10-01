package com.franctan.mypassport.ui.main.listprofilesfragment

import com.franctan.firebaserepo.daos.ProfilesListDao
import com.franctan.models.Profile
import com.franctan.mypassport.preferences.RxPreferences
import com.franctan.utilities.IoScheduler
import com.franctan.utilities.MainScheduler
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.Function3
import javax.inject.Inject

class ListProfilesObserver
@Inject constructor(
        private val profilesDao: ProfilesListDao,
        private val rxPreferences: RxPreferences,
        private val listSorter: ListSorter,
        @IoScheduler private val ioScheduler: Scheduler,
        @MainScheduler private val mainScheduler: Scheduler
) {

    fun observeProfiles(): Observable<List<Profile>> {

        val profilesDaoRx = profilesDao.getProfileListObserver()
        val filterRx = rxPreferences.getFilterAsObservable()
        val sortRx = rxPreferences.getSortAsObservable()

        return Observable
                .combineLatest(profilesDaoRx, filterRx, sortRx,
                        Function3<List<Profile>, String, String, List<Profile>>
                        { profiles, filter, sort ->
                            listSorter.filterAndSort(profiles, filter, sort)
                        })
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)


    }

}


