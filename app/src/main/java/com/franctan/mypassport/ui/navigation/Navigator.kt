package com.franctan.mypassport.ui.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.franctan.mypassport.R
import com.franctan.mypassport.ui.main.viewprofilefragment.ViewProfileFragment
import javax.inject.Inject

class Navigator
@Inject constructor(
        private val activity: AppCompatActivity
) {

    fun goToViewProfile(profileId: String) {
        val fragment = ViewProfileFragment.newInstance(profileId)
        replaceFragment(fragment)

    }

    private fun replaceFragment(newFragment: Fragment) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_down, R.anim.slide_out_down)
                .addToBackStack(newFragment::class.simpleName)
                .replace(R.id.container, newFragment, newFragment::class.simpleName)
//                .add(R.id.container, newFragment, newFragment::class.simpleName)
//                .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                .commit()

    }

}


