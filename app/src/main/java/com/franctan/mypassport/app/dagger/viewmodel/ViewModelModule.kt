package com.franctan.mypassport.app.dagger.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.franctan.lonelyplanetcurrencyguide.injection.view_model.ViewModelFactory

import com.franctan.mypassport.ui.main.editprofilefragment.EditProfileViewModel
import com.franctan.mypassport.ui.main.listprofilesfragment.ListProfilesViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    abstract fun bindEditProfileViewModel(model: EditProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListProfilesViewModel::class)
    abstract fun bindListProfilesViewModell(model: ListProfilesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
