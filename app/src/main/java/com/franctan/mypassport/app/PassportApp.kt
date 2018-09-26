package com.franctan.mypassport.app

import android.app.Activity
import android.app.Application
import com.franctan.mypassport.app.dagger.AppComponent
import com.franctan.mypassport.app.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


class PassportApp : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        injectDagger()
    }


    private fun injectDagger() {
        DaggerAppComponent.builder().bindApplication(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }


}