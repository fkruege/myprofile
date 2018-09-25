package com.franctan.mypassport.ui.common

import android.content.Context
import javax.inject.Inject


class ContextHelper
@Inject constructor(private val context: Context) {

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

}