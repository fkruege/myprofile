package com.franctan.utilities

import java.util.*
import javax.inject.Inject


class UUIDGenerator
@Inject constructor() {

    fun generate() : UUID {
        return UUID.randomUUID()
    }

}


