package com.franctan.firebaserepo.dagger

import com.franctan.firebaserepo.PROFILES_DB
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Logger
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ProfilesDatabaseReference

@Module
class FirebaseRepoModule {

    @Provides
    @Singleton
    @ProfilesDatabaseReference
    fun provideDatabaseReference(): DatabaseReference {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.setLogLevel(Logger.Level.DEBUG)
        firebaseDatabase.setPersistenceEnabled(true)
        return firebaseDatabase.reference.child(PROFILES_DB)
    }

}