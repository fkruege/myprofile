package com.franctan.models


enum class Gender {
    Male, Female
}

fun String.toGender(): Gender {
    return Gender.valueOf(this)
}

