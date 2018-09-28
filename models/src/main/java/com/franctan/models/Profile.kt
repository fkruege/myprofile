package com.franctan.models

import java.util.Collections.emptyList


data class Profile(
        val id: String,
        val firstName: String,
        val lastName: String,
        val age: Int,
        val gender: Gender,
        val hobbyList: List<String>,
        val profilePhotoPath: String
) {
    companion object {
        fun EMPTY(): Profile {
            return Profile("", "", "", 0, Gender.M, emptyList(), "")
        }
    }

}

