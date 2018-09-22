package com.franctan.mypassport.ui.main.dagger

import com.franctan.mypassport.ui.main.MainActivity
import com.franctan.mypassport.ui.main.listprofilesfragment.ListProfilesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [(ListProfilesFragmentModule::class)])
    abstract fun contributeMainActivity(): MainActivity

}

@Module
abstract class ListProfilesFragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributesListProfilesFragment(): ListProfilesFragment

}