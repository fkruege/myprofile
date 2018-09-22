package com.franctan.firebaserepo.daos

import com.franctan.models.Gender
import com.franctan.models.Profile
import com.google.firebase.database.*
import com.nhaarman.mockito_kotlin.any
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks

class ProfilesDaoTest {

    @Mock
    private lateinit var dbReference: DatabaseReference

    @Mock
    private lateinit var profilesMapper: ProfilesSnapShotMapper

    @Mock
    private lateinit var dbError: DatabaseError

    @Mock
    private lateinit var parentSnapShot: DataSnapshot

    @Mock
    private lateinit var childSnapShot: DataSnapshot

    @InjectMocks
    private lateinit var dao: ProfilesDao

    private val profile1 = Profile("-KMZ_aCNCCyvTPiVA3wK", "", "", 1, DateTime(1), Gender.M, emptyList())

    @Before
    fun setUp() {
        initMocks(this)
    }


    @Test
    fun getProfilesHappy() {
        `when`(parentSnapShot.children).thenReturn(listOf(childSnapShot))

        `when`(profilesMapper.tryToMapToProfile(any())).thenReturn(profile1)

        `when`(dbReference.addListenerForSingleValueEvent(any())).thenAnswer { invocation ->
            val callback: ValueEventListener = invocation!!.getArgument<ValueEventListener>(0)
            callback.onDataChange(parentSnapShot)
        }

        dao.getProfiles().test().assertComplete().assertValue { list -> list.count() == 1 }
    }

    @Test
    fun getProfilesExceptionThrown() {
        `when`(parentSnapShot.children).thenReturn(listOf(childSnapShot))

        `when`(profilesMapper.tryToMapToProfile(any())).thenThrow(NullPointerException(""))

        `when`(dbReference.addListenerForSingleValueEvent(any())).thenAnswer { invocation ->
            val callback: ValueEventListener = invocation!!.getArgument<ValueEventListener>(0)
            callback.onDataChange(parentSnapShot)
        }

        dao.getProfiles().test().assertError { error -> error is NullPointerException }
    }


    @Test
    fun getProfilesError() {
        `when`(dbError.toException()).thenReturn(DatabaseException(""))

        `when`(dbReference.addListenerForSingleValueEvent(any())).thenAnswer { invocation ->
            val callback: ValueEventListener = invocation!!.getArgument<ValueEventListener>(0)
            callback.onCancelled(dbError)
        }

        dao.getProfiles().test().assertError { error -> error is DatabaseException }
    }
}