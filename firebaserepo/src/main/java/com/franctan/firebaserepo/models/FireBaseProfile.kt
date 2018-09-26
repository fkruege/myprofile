package com.franctan.firebaserepo.models

import com.franctan.models.Profile
import com.franctan.models.toGender
import org.joda.time.DateTime

const val HOBBIES_DELIMITER = ';'


data class FireBaseProfile(
        val firstname: String = ""
        , val lastname: String = ""
        , val age: String = ""
        , val datecreated: String = ""
        , val gender: String = ""
        , val hobbies: String = ""
        , val profilePhotoPath: String = ""
)

fun FireBaseProfile.mapToProfile(key: String): Profile {
    val profileId = key
    val firstname = this.firstname
    val lastname = this.lastname
    val age = this.age.toInt()
    val gender = this.gender.toGender()
    val hobbies = this.hobbies.toHobbiesList()
    val profilePhotoPath = this.profilePhotoPath
    val dateCreated = DateTime(this.datecreated.toLong())

    return Profile(
            profileId
            , firstname
            , lastname
            , age
            , dateCreated
            , gender
            , hobbies
            , profilePhotoPath
    )
}

fun Profile.mapToFireBaseProfile(): FireBaseProfile {
    val profileId = this.id
    val firstName = this.firstName
    val lastName = this.lastName
    val age: String = this.age.toString()
    val gender: String = this.gender.toString()
    val hobbies: String = this.hobbyList.joinToString(HOBBIES_DELIMITER.toString())
    val profilePhotoPath: String = this.profilePhotoPath
    val dateCreated: String = this.dateCreated.millis.toString()

    return FireBaseProfile(firstName, lastName, age, dateCreated, gender, hobbies, profilePhotoPath)
}

fun String.toHobbiesList(): List<String> {
    val trimmed = this.trim()
    return if (trimmed.isEmpty()) {
        emptyList()
    } else {
        trimmed.split(com.franctan.firebaserepo.models.HOBBIES_DELIMITER)
    }
}