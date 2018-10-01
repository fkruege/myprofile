package com.franctan.mypassport.ui.main.listprofilesfragment

import com.franctan.firebaserepo.daos.ProfilesListDao
import com.franctan.models.Profile
import com.franctan.mypassport.preferences.RxPreferences
import com.franctan.mypassport.ui.main.listprofilesfragment.TestUtils.Companion.TEST_PROFILE_LIST
import com.nhaarman.mockito_kotlin.eq
import io.reactivex.Observable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks

class ListProfilesObserverTest {

    @Mock
    private lateinit var profilesDao: ProfilesListDao
    @Mock
    private lateinit var rxPreferences: RxPreferences
    @Mock
    private lateinit var listSorter: ListSorter

    private val ioScheduler = Schedulers.trampoline()
    private val mainScheduler = Schedulers.trampoline()

    private lateinit var listProfilesObserver: ListProfilesObserver

    @Before
    fun setUp() {
        initMocks(this)
        listProfilesObserver = ListProfilesObserver(profilesDao, rxPreferences, listSorter, ioScheduler, mainScheduler)
    }

    @Test
    fun observeProfiles() {
        `when`(profilesDao.getProfileListObserver()).thenReturn(Observable.just(TEST_PROFILE_LIST))
        `when`(rxPreferences.getFilterAsObservable()).thenReturn(Observable.just("filter"))
        `when`(rxPreferences.getSortAsObservable()).thenReturn(Observable.just("sort"))

        `when`(listSorter.filterAndSort(eq(TEST_PROFILE_LIST), eq("filter"), eq("sort"))).thenReturn(TEST_PROFILE_LIST)

        listProfilesObserver.observeProfiles().test().assertComplete().assertValue { result -> result == TEST_PROFILE_LIST }
    }
}