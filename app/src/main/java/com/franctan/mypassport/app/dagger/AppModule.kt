package com.franctan.mypassport.app.dagger

import android.content.Context
import com.franctan.mypassport.app.PassportApp
import com.franctan.mypassport.app.dagger.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides


@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    fun provideApplicationContext(app: PassportApp) : Context {
        return app.applicationContext
    }


}