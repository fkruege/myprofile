package com.franctan.mypassport.ui.main.dagger

import android.app.Activity
import com.franctan.mypassport.ui.main.MainActivity
import com.franctan.mypassport.ui.main.editprofilefragment.dagger.EditProfileFragmentModule
import com.franctan.mypassport.ui.main.listprofilesfragment.ListProfilesFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [(MainActivityBindsModule::class)
        , (ListProfilesFragmentModule::class), (EditProfileFragmentModule::class)])
    abstract fun contributeMainActivity(): MainActivity

}

@Module
abstract class MainActivityBindsModule {
    @Binds
    abstract fun bindMainActivityToActivity(activity: MainActivity): Activity
}

@Module
abstract class ListProfilesFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesListProfilesFragment(): ListProfilesFragment

}


