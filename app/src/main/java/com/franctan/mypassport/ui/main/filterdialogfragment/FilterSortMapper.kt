package com.franctan.mypassport.ui.main.filterdialogfragment

import android.content.Context
import com.franctan.mypassport.R
import javax.inject.Inject

class FilterSortMapper
@Inject constructor(private val context: Context) {

    fun mapFilter(value: String): FilterOptions {
        return if (value == getString(R.string.males_only)) {
            FilterOptions.MALES_ONLY
        } else if (value == getString(R.string.females_only)) {
            FilterOptions.FEMALES_ONLY
        } else {
            FilterOptions.default()
        }
    }


    fun mapSort(value: String): SortOptions {
        return if (value == getString(R.string.name_descending)) {
            SortOptions.NAME_DESC
        } else if (value == getString(R.string.age_ascending)) {
            SortOptions.AGE_ASC
        } else if (value == getString(R.string.age_descending)) {
            SortOptions.AGE_DESC
        } else {
            SortOptions.default()
        }
    }

    fun getSortStringDefault(): String {
        return context.getString(R.string.default_name_sort)
    }

    fun getFilterStringDefault(): String {
        return context.getString(R.string.default_filter)
    }


    private fun getString(id: Int): String {
        return context.getString(id)
    }


}


