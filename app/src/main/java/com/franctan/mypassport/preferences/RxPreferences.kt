package com.franctan.mypassport.preferences

import android.content.Context
import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.franctan.mypassport.R
import io.reactivex.Observable
import javax.inject.Inject


class RxPreferences
@Inject constructor(
        private val context: Context,
        private val rxSharedPreferences: RxSharedPreferences
) {
    companion object {
        val FILTER_KEY = "filter"
        val SORT_KEY = "sort"
    }


    fun setFilter(value: String) {
        mGetFilter().set(value)
    }

    fun getFilter(): String {
        return mGetFilter().get()
    }

    fun getFilterAsObservable(): Observable<String> {
        return mGetFilter().asObservable()
    }

    fun setSort(value: String) {
        mGetSort().set(value)
    }


    fun getSort(): String {
        return mGetSort().get()
    }

    fun getSortAsObservable(): Observable<String> {
        return mGetSort().asObservable()
    }

    private fun mGetFilter(): Preference<String> {
        val default = context.getString(R.string.default_filter)
        return getPref(FILTER_KEY, default)
    }

    private fun mGetSort(): Preference<String> {
        val default = context.getString(R.string.default_name_sort)
        return rxSharedPreferences.getString(SORT_KEY, default)
    }

    private fun getPref(key: String, defaultValue: String): Preference<String> {
        return rxSharedPreferences.getString(key, defaultValue)
    }


}


