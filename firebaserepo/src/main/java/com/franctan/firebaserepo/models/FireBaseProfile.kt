package com.franctan.firebaserepo.models

import com.franctan.models.Profile
import com.franctan.models.toGender

const val HOBBIES_DELIMITER = ';'
const val FIRST_NAME = "firstname"
const val LAST_NAME = "lastname"
const val AGE = "age"
const val GENDER = "gender"
const val HOBBIES = "hobbies"
const val PROFILE_PHOTO_PATH = "profilePhotoPath"


data class FireBaseProfile(
        val firstname: String = ""
        , val lastname: String = ""
        , val age: String = ""
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

    return Profile(
            profileId
            , firstname
            , lastname
            , age
            , gender
            , hobbies
            , profilePhotoPath
    )
}

fun Profile.mapToUpdateMap(): Map<String, Any> {
    val updateMap = mutableMapOf<String, Any>()
    updateMap[FIRST_NAME] = this.firstName
    updateMap[LAST_NAME] = this.lastName
    updateMap[AGE] = this.age.toString()
    updateMap[GENDER] = this.gender.toString()
    updateMap[HOBBIES] = this.hobbyList.joinToString(HOBBIES_DELIMITER.toString())
    updateMap[PROFILE_PHOTO_PATH] = this.profilePhotoPath
    return updateMap
}

fun Map<String, String>.mapToFirebaseProfile(): FireBaseProfile {
    val firstName = this[FIRST_NAME] ?: ""
    val lastName = this[LAST_NAME] ?: ""
    val age: String = this[AGE] ?: ""
    val gender: String = this[GENDER] ?: ""
    val hobbies: String = this[HOBBIES] ?: ""
    val profilePhotoPath: String = this[PROFILE_PHOTO_PATH] ?: ""

    return FireBaseProfile(firstName, lastName, age, gender, hobbies, profilePhotoPath)
}

fun Profile.mapToFireBaseProfile(): FireBaseProfile {
    val profileId = this.id
    val firstName = this.firstName
    val lastName = this.lastName
    val age: String = this.age.toString()
    val gender: String = this.gender.toString()
    val hobbies: String = this.hobbyList.joinToString(HOBBIES_DELIMITER.toString())
    val profilePhotoPath: String = this.profilePhotoPath

    return FireBaseProfile(firstName, lastName, age, gender, hobbies, profilePhotoPath)
}

internal fun String.toHobbiesList(): List<String> {
    val trimmed = this.trim()
    return if (trimmed.isEmpty()) {
        emptyList()
    } else {
        trimmed.split(com.franctan.firebaserepo.models.HOBBIES_DELIMITER)
    }
}