package com.franctan.mypassport.app.dagger

import com.franctan.firebaserepo.dagger.FirebaseRepoModule
import com.franctan.mypassport.app.PassportApp
import com.franctan.mypassport.ui.main.dagger.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules =
[
    (AndroidInjectionModule::class)
    , (AppModule::class)
    , (FirebaseRepoModule::class)
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