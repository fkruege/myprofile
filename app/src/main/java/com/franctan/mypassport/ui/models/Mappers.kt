package com.franctan.mypassport.ui.models

import com.franctan.models.Profile
import org.joda.time.DateTime

fun UIProfileModel.mapToProfile(): Profile {

    val profileId = this.profileId
    val firstname = this.firstName
    val lastname = this.lastName
    val age = this.age
    val gender = this.gender
    val hobbies = this.hobbyList.toList()
    val profilePhotoPath = this.profilePhotoPath
    val dateCreated = DateTime.now()

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


