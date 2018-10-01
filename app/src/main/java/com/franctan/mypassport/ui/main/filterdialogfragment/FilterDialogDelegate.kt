package com.franctan.mypassport.ui.main.filterdialogfragment

import android.content.Context
import com.franctan.mypassport.R
import com.franctan.mypassport.preferences.RxPreferences
import javax.inject.Inject

class FilterDialogDelegate
@Inject constructor(
        private val context: Context,
        private val sharedPrefs: RxPreferences) {


    fun updatePreferences(choices: Array<String>, which: Int) {
        val selected = choices[which]
        sharedPrefs.setFilter(selected)
    }

    fun getChoices(): Array<String> {
        return context.resources.getStringArray(R.array.filter_options)
    }

    fun getSelectedIndex(choices: Array<String>): Int {
        val current = sharedPrefs.getFilter()
        val index = choices.indexOf(current)
        if (index >= 0) {
            return index
        } else {
            return 0
        }
    }

}


