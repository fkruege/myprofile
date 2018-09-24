package com.franctan.mypassport.ui.converters

import android.databinding.InverseMethod
import javax.inject.Inject

class IntConverter
@Inject constructor() {

    @InverseMethod("toInt")
    fun toString(value: Int): String {
        return value.toString()
    }

    fun toInt(value: String): Int {
        return Integer.parseInt(value)
    }
}
