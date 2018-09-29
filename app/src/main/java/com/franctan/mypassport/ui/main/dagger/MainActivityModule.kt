package com.franctan.mypassport.ui.main.dagger

import android.support.v7.app.AppCompatActivity
import com.franctan.mypassport.ui.main.MainActivity
import com.franctan.mypassport.ui.main.editprofilefragment.dagger.EditProfileFragmentModule
import com.franctan.mypassport.ui.main.listprofilesfragment.dagger.ListProfilesFragmentModule
import com.franctan.mypassport.ui.main.viewprofilefragment.dagger.ViewProfileFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [(MainActivityBindsModule::class)
        , (ListProfilesFragmentModule::class), (ViewProfileFragmentModule::class), (EditProfileFragmentModule::class)])
    abstract fun contributeMainActivity(): MainActivity

}

@Module
abstract class MainActivityBindsModule {
    @Binds
    abstract fun bindMainActivityToAppCompatActivity(activity: MainActivity): AppCompatActivity
}


