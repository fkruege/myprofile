package com.franctan.models

import org.joda.time.DateTime
import java.util.*


data class Profile(
        val id: String,
        val firstName: String,
        val lastName: String,
        val age: Int,
        val dateCreated: DateTime,
        val gender: Gender,
        val hobbyList: List<String>
)
