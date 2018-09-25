package com.franctan.mypassport.ui.main.editprofilefragment.dagger

import android.support.v4.app.Fragment
import com.franctan.mypassport.ui.main.editprofilefragment.EditProfileFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class EditProfileFragmentModule {
    @ContributesAndroidInjector
    (modules = [(EditProfileFragmentBindingModule::class)])
    abstract fun contributesEditProfileFragment(): EditProfileFragment
}

@Module
abstract class EditProfileFragmentBindingModule {
    @Binds
    abstract fun bindEditProfileFragment(fragment: EditProfileFragment): Fragment
}