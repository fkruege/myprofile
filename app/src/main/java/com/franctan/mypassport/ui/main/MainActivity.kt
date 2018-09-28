package com.franctan.mypassport.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.franctan.mypassport.R
import com.franctan.mypassport.ui.main.editprofilefragment.EditProfileFragment
import com.franctan.mypassport.ui.main.listprofilesfragment.ListProfilesFragment
import com.franctan.mypassport.ui.main.viewprofilefragment.ViewProfileFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, EditProfileFragment.newInstance(""))
                    .replace(R.id.container, ListProfilesFragment.newInstance())
//                    .replace(R.id.container, ViewProfileFragment.newInstance(""))
                    .commit()
        }


    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return androidInjector
    }
}
