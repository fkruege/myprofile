package com.franctan.firebaserepo.utils

import com.franctan.models.Gender
import com.google.firebase.database.DataSnapshot


fun DataSnapshot.parseInt(key: String, default: Int = Integer.MIN_VALUE): Int {
    return this.child(key).value?.toString()?.toInt() ?: default
}

fun DataSnapshot.parseLong(key: String, default: Long = Long.MIN_VALUE): Long {
    return this.child(key).value?.toString()?.toLong() ?: default
}

fun DataSnapshot.parseString(key: String, default: String = ""): String {
    return this.child(key).value?.toString() ?: default
}

fun DataSnapshot.parseGender(key: String, default: Gender = Gender.M): Gender {
    return this.child(key).value?.toString()?.let { value -> Gender.valueOf("") } ?: default
}
