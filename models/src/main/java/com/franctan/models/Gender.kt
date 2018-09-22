package com.franctan.models


enum class Gender {
    M, F
}

public fun String.toGender(): Gender {
    return Gender.valueOf(this)
}


//public fun String.toGender(): Gender {
//    return try {
//        Gender.valueOf(this)
//    } catch (ex: IllegalStateException) {
//        Gender.M
//    }
//
//}
