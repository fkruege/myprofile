package com.franctan.mypassport.app.dagger

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.franctan.mypassport.app.PassportApp
import com.franctan.mypassport.app.dagger.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    fun provideApplicationContext(app: PassportApp): Context {
        return app.applicationContext
    }

    @Provides
    fun providePreferencesManager(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideRxSharedPreferences(sharedPreferences: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(sharedPreferences)
    }


}