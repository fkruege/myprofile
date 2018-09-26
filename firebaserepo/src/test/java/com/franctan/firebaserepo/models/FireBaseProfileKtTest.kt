package com.franctan.firebaserepo.models

import com.franctan.models.Gender
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FireBaseProfileKtTest {

    private val profileId = "-KMZ_aCNCCyvTPiVA3wN"
    private val john = "john"
    private val doe = "doe"
    private val age3 = "3"
    private val date1 = "1"
    private val dance = "dance"
    private val sing = "sing"

    private val fbProfileGood =
            FireBaseProfile(
                    firstname = john
                    , lastname = doe
                    , age = age3
                    , datecreated = date1
                    , gender = "M"
                    , hobbies = "$dance;$sing"
            )

    val fbProfileUnknownGenderEnum =
                FireBaseProfile(
                        firstname = john
                        , lastname = doe
                        , age = age3
                        , datecreated = date1
                        , gender = ""
                        , hobbies = "$dance;$sing"
                )



    @Before
    fun setUp() {
    }

    @Test
    fun mapToProfileGood() {

        val mappedProfile = fbProfileGood.mapToProfile(profileId)
        assertTrue(mappedProfile.id == profileId)
        assertEquals(john, mappedProfile.firstName)
        assertEquals(doe, mappedProfile.lastName)
        assertEquals(age3.toInt(), mappedProfile.age)
        assertEquals(DateTime(date1.toLong()), mappedProfile.dateCreated)
        assertEquals(Gender.M, mappedProfile.gender)
        assertTrue(mappedProfile.hobbyList.isNotEmpty())

    }

    @Test(expected = IllegalArgumentException::class)
    fun mapToProfileUnknownGenderEnum() {
        fbProfileUnknownGenderEnum.mapToProfile(profileId)
    }

    @Test(expected = NumberFormatException::class)
    fun mapToProfileBadAge() {
        val badAgeProfile = fbProfileGood.copy(age = "bad")
        badAgeProfile.mapToProfile(profileId)
    }

    @Test(expected = NumberFormatException::class)
    fun mapToProfileBadDate1() {
        val badDateProfile = fbProfileGood.copy(datecreated = "abc")
        badDateProfile.mapToProfile(profileId)
    }

    @Test(expected = NumberFormatException::class)
    fun mapToProfileBadDate2() {
        val badDateProfile = fbProfileGood.copy(datecreated = "")
        badDateProfile.mapToProfile(profileId)
    }

    @Test
    fun toHobbiesListGood() {
        val hobbies = "$dance;$sing"
        val toHobbiesList = hobbies.toHobbiesList()

        assertEquals(2, toHobbiesList.size)
        assertTrue(toHobbiesList.contains(dance))
        assertTrue(toHobbiesList.contains(sing))
    }

    @Test
    fun toHobbiesListEmpty() {
        val hobbies = ""
        val toHobbiesList = hobbies.toHobbiesList()
        assertEquals(0, toHobbiesList.size)
    }

    @Test
    fun toHobbiesListSingle() {
        val hobbies = "$dance"
        val toHobbiesList = hobbies.toHobbiesList()
        assertEquals(1, toHobbiesList.size)
        assertTrue(toHobbiesList.contains(dance))
    }
}