package com.franctan.mypassport.ui.models

import com.franctan.models.Profile

fun UIProfileModel.mapToProfile(): Profile {

    val profileId = this.profileId
    val firstname = this.firstName.trim()
    val lastname = this.lastName.trim()
    val age = this.age.toInt()
    val gender = this.gender
    val hobbies = this.hobbyList.filterHobbies()
    val profilePhotoPath = this.profilePhotoPath.trim()

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

private fun List<String>.filterHobbies(): List<String> {
    val hobbies = mutableListOf<String>()
    for (hobby in this) {
        val trimmedHobby = hobby.trim()
        if (trimmedHobby.isNotEmpty()) {
            hobbies.add(trimmedHobby)
        }
    }

    return hobbies
}

private fun String.toInt(): Int {
    return try {
        Integer.parseInt(this)
    } catch (ex: NumberFormatException) {
        0
    }
}




