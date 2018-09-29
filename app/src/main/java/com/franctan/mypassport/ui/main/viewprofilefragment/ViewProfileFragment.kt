package com.franctan.mypassport.ui.main.viewprofilefragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.franctan.lonelyplanetcurrencyguide.injection.view_model.ViewModelFactory
import com.franctan.mypassport.databinding.FragmentViewProfileBinding
import com.franctan.mypassport.ui.common.Constants.Companion.PROFILE_ID_KEY
import com.franctan.mypassport.ui.navigation.Navigator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_view_profile.*
import javax.inject.Inject

class ViewProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigator: Navigator

    private lateinit var viewProfileViewModel: ViewProfileViewModel

    lateinit var viewProfileBinding: FragmentViewProfileBinding


    companion object {
        fun newInstance(profileId: String): ViewProfileFragment {
            val arguments = Bundle()
            arguments.putString(PROFILE_ID_KEY, profileId)
            val fragment = ViewProfileFragment()
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

        arguments?.let { inArgs ->
            inArgs[PROFILE_ID_KEY]?.let { inProfileKey ->
                viewProfileViewModel.loadUser(inProfileKey as String)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewProfileBinding = FragmentViewProfileBinding.inflate(inflater, container, false)
        viewProfileBinding.viewProfileViewModel = viewProfileViewModel
        return viewProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        listenForEditProfileEvent()
    }

    private fun loadViewModel() {
        viewProfileViewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewProfileViewModel::class.java)
    }

    private fun setToolbar() {
        val toolbar = vwToolbar
        val parentActivity: AppCompatActivity = this.activity as AppCompatActivity
        parentActivity.setSupportActionBar(toolbar)
        parentActivity.supportActionBar?.setHomeButtonEnabled(true)
        parentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            navigator.goBack()

        }
    }

    private fun listenForEditProfileEvent() {
        viewProfileViewModel.editProfileEvent.observe(this, Observer<String> { profileId ->
            profileId?.let { inProfileId -> navigator.goToEditProfile(inProfileId) }
        })
    }


}
