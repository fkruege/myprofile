package com.franctan.mypassport.ui.main.editprofilefragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franctan.lonelyplanetcurrencyguide.injection.view_model.ViewModelFactory
import com.franctan.mypassport.R
import com.franctan.mypassport.databinding.FragmentEditProfileBinding
import com.franctan.mypassport.ui.converters.IntConverter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import javax.inject.Inject

class EditProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var intConverter: IntConverter

    private lateinit var editProfileViewModel: EditProfileViewModel

    lateinit var editProfileBinding: FragmentEditProfileBinding

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
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        loadViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            //            val profileId: String? = savedInstanceState.getString(PROFILE_ID_KEY)
//            profileId?.let { editViewModel.setProfileId(it) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        editProfileBinding = FragmentEditProfileBinding.inflate(inflater, container, false)
        editProfileBinding.editProfileViewModel = editProfileViewModel
        editProfileBinding.intConverter = intConverter
        return editProfileBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
    }

    private fun loadViewModel() {
        editProfileViewModel = ViewModelProviders.of(this, viewModelFactory).get(EditProfileViewModel::class.java)
    }

    private fun setToolbar() {
        val toolbar = vwToolbar
        val parentActivity: AppCompatActivity = this.activity as AppCompatActivity
        parentActivity.setSupportActionBar(toolbar)
    }
}
