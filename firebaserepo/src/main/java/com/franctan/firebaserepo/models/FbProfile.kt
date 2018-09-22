package com.franctan.firebaserepo.models

import com.franctan.models.Profile
import com.franctan.models.toGender
import org.joda.time.DateTime

const val HOBBIES_DELIMITER = ';'


data class FbProfile(
        val firstname: String = ""
        , val lastname: String = ""
        , val age: String = ""
        , val datecreated: String = ""
        , val gender: String = ""
        , val hobbies: String = ""
)

fun FbProfile.mapToProfile(key: String): Profile {
    val profileId = key
    val firstname = this.firstname
    val lastname = this.lastname
    val age = this.age.toInt()
    val gender = this.gender.toGender()
    val hobbies = this.hobbies.toHobbiesList()
    val dateCreated = DateTime(this.datecreated.toLong())

    return Profile(
            profileId
            , firstname
            , lastname
            , age
            , dateCreated
            , gender
            , hobbies
    )
}

fun String.toHobbiesList(): List<String> {
    val trimmed = this.trim()
    return if (trimmed.isEmpty()) {
        emptyList()
    } else {
        trimmed.split(com.franctan.firebaserepo.models.HOBBIES_DELIMITER)
    }
}