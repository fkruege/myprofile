package com.franctan.mypassport.app.dagger

import com.franctan.firebasecloudstorage.dagger.FirebaseCloudStorageModule
import com.franctan.firebaserepo.dagger.FirebaseRepoModule
import com.franctan.mypassport.app.PassportApp
import com.franctan.mypassport.ui.main.dagger.MainActivityModule
import com.franctan.utilities.dagger.UtilitiesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules =
[
    (AndroidSupportInjectionModule::class)
    , (AppModule::class)
    , (UtilitiesModule::class)
    , (FirebaseRepoModule::class)
    , (FirebaseCloudStorageModule::class)
    , (MainActivityModule::class)
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication(app: PassportApp): Builder

        fun build(): AppComponent
    }

    fun inject(myApp: PassportApp)

}