package com.franctan.mypassport.ui.main.filterdialogfragment.dagger

import com.franctan.mypassport.ui.main.filterdialogfragment.FilterDialogFragment
import com.franctan.mypassport.ui.main.filterdialogfragment.SortDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FilterDialogFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesFilterDialogFragment(): FilterDialogFragment
}

@Module
abstract class SortDialogFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesSortDialogFragment(): SortDialogFragment
}
