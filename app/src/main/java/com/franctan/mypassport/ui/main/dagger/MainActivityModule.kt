package com.franctan.mypassport.ui.main.dagger

import com.franctan.mypassport.ui.main.MainActivity
import com.franctan.mypassport.ui.main.editprofilefragment.EditProfileFragment
import com.franctan.mypassport.ui.main.listprofilesfragment.ListProfilesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [(ListProfilesFragmentModule::class), (EditProfileFragmentModule::class)])
    abstract fun contributeMainActivity(): MainActivity

}

@Module
abstract class ListProfilesFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesListProfilesFragment(): ListProfilesFragment

}

@Module
abstract class EditProfileFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesEditProfileFragment(): EditProfileFragment

}
