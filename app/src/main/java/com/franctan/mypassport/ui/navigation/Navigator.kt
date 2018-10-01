package com.franctan.mypassport.ui.navigation

import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.franctan.mypassport.R
import com.franctan.mypassport.ui.main.editprofilefragment.EditProfileFragment
import com.franctan.mypassport.ui.main.filterdialogfragment.FilterDialogFragment
import com.franctan.mypassport.ui.main.filterdialogfragment.SortDialogFragment
import com.franctan.mypassport.ui.main.listprofilesfragment.ListProfilesFragment
import com.franctan.mypassport.ui.main.viewprofilefragment.ViewProfileFragment
import javax.inject.Inject

class Navigator
@Inject constructor(
        private val activity: AppCompatActivity
) {

    fun goToListProfiles() {
        val fragment = ListProfilesFragment.newInstance()
        replaceFragment(fragment)
    }


    fun goBackToListProfiles() {
        activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun goToViewProfile(profileId: String) {
        val fragment = ViewProfileFragment.newInstance(profileId)
        replaceFragment(fragment)
    }

    fun goBackToViewProfile(profileId: String) {
        val fragmentManager = activity.supportFragmentManager
        val findFragment = fragmentManager.findFragmentByTag(ViewProfileFragment::class.simpleName)
        if (findFragment == null) {
            activity.supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val fragment = ViewProfileFragment.newInstance(profileId)
            replaceFragment(fragment)
        } else {
            fragmentManager.popBackStack()
        }
    }

    fun goToEditProfile(profileId: String) {
        val fragment = EditProfileFragment.newInstance(profileId)
        replaceFragment(fragment)
    }

    fun goToFilterDialog() {
        val dialog = FilterDialogFragment.newInstance()
        displayDialogFragment(dialog)
    }

    fun goToSortDialog() {
        val dialog = SortDialogFragment.newInstance()
        displayDialogFragment(dialog)
    }


    fun goBack() {
        activity.supportFragmentManager.popBackStack()
    }


    private fun replaceFragment(newFragment: Fragment) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_down, R.anim.slide_out_down)
                .addToBackStack(newFragment::class.simpleName)
                .replace(R.id.container, newFragment, newFragment::class.simpleName)
                .commit()
    }

    private fun replaceFragmentNoBackStack(newFragment: Fragment) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_down, R.anim.slide_out_down)
                .replace(R.id.container, newFragment, newFragment::class.simpleName)
                .commit()
    }


    private fun displayDialogFragment(dialogFragment: DialogFragment) {
        val fragmentManager = activity.supportFragmentManager
        val tag = dialogFragment::class.java.simpleName
        if (fragmentManager.findFragmentByTag(tag) == null) {
            dialogFragment.show(fragmentManager, tag)
        }
    }


}


