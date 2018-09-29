package com.franctan.mypassport.ui.main.viewprofilefragment.dagger

import android.support.v4.app.Fragment
import com.franctan.mypassport.ui.main.viewprofilefragment.ViewProfileFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ViewProfileFragmentModule {
    @ContributesAndroidInjector
    (modules = [(ViewProfileFragmentBindingModule::class)])
    abstract fun contributesViewProfileFragment(): ViewProfileFragment
}

@Module
abstract class ViewProfileFragmentBindingModule {
    @Binds
    abstract fun bindViewProfileFragment(fragment: ViewProfileFragment): Fragment
}