package com.franctan.models


data class Profile(
        val id: String,
        val firstName: String,
        val lastName: String,
        val age: Int,
        val gender: Gender,
        val hobbyList: List<String>,
        val profilePhotoPath: String
)
