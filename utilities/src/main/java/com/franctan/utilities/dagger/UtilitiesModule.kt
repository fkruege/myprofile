package com.franctan.utilities.dagger

import com.franctan.utilities.ComputationScheduler
import com.franctan.utilities.IoScheduler
import com.franctan.utilities.MainScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class UtilitiesModule {
    @Provides
    @ComputationScheduler
    fun provideComputationScheduler(): Scheduler {
        return Schedulers.computation()
    }

    @Provides
    @IoScheduler
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @MainScheduler
    fun provideMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }


}


