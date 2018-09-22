package com.franctan.mypassport.app.dagger

import android.content.Context
import com.franctan.mypassport.app.PassportApp
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideApplicationContext(app: PassportApp) : Context {
        return app.applicationContext
    }


}