package com.franctan.mypassport.ui.main.listprofilesfragment.dagger

import android.support.v4.app.Fragment
import com.franctan.mypassport.ui.main.editprofilefragment.EditProfileFragment
import com.franctan.mypassport.ui.main.listprofilesfragment.ListProfilesFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ListProfilesFragmentModule {
    @ContributesAndroidInjector
    (modules = [(ListProfilesFragmentBindingModule::class)])
    abstract fun contributesListProfilesFragment(): ListProfilesFragment
}

@Module
abstract class ListProfilesFragmentBindingModule {
    @Binds
    abstract fun bindListProfilesFragment(fragment: ListProfilesFragment): Fragment
}