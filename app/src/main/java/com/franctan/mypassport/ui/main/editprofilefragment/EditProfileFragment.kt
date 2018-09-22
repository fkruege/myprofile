package com.franctan.mypassport.ui.main.editprofilefragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franctan.mypassport.R
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class EditProfileFragment : Fragment() {

    companion object {
        const val PROFILE_ID_KEY = "profileid"
        fun newInstance(profileId: String): EditProfileFragment {
            val arguments = Bundle()
            arguments.putString(PROFILE_ID_KEY, profileId)

            val fragment = EditProfileFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = toolbar
        val parentActivity : AppCompatActivity = this.activity as AppCompatActivity
        parentActivity.setSupportActionBar(toolbar)
    }
}
