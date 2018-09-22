package com.franctan.firebaserepo.dagger

import com.franctan.firebaserepo.PROFILES_DB
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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
    @ProfilesDatabaseReference
    fun provideDatabaseReference(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference.child(PROFILES_DB)
    }

}