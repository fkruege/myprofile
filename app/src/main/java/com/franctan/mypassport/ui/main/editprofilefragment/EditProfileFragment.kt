package com.franctan.mypassport.ui.main.editprofilefragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.franctan.lonelyplanetcurrencyguide.injection.view_model.ViewModelFactory
import com.franctan.models.Profile
import com.franctan.mypassport.R
import com.franctan.mypassport.databinding.FragmentEditProfileBinding
import com.franctan.mypassport.ui.common.Constants.Companion.PROFILE_ID_KEY
import com.franctan.mypassport.ui.common.SnackBarMsgDisplayer
import com.franctan.mypassport.ui.navigation.Navigator
import com.franctan.mypassport.ui.permissions.PermissionHelper
import com.franctan.mypassport.ui.photos.PhotoChooser
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import javax.inject.Inject


class EditProfileFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var permissionHelper: PermissionHelper

    @Inject
    lateinit var photoChooser: PhotoChooser

    @Inject
    lateinit var snackBarMsgDisplayer: SnackBarMsgDisplayer

    @Inject
    lateinit var navigator: Navigator

    private lateinit var editProfileViewModel: EditProfileViewModel

    lateinit var editProfileBinding: FragmentEditProfileBinding

    private var btnDelete: MenuItem? = null

    companion object {
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
        arguments?.let { inArgs ->
            inArgs[PROFILE_ID_KEY]?.let { inProfileId ->
                val profileId = inProfileId as String
                if (profileId.isNotEmpty()) {
                    editProfileViewModel.loadUser(profileId)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        editProfileBinding = FragmentEditProfileBinding.inflate(inflater, container, false)
        editProfileBinding.editProfileViewModel = editProfileViewModel
        return editProfileBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        listenForChoosePhotoEvent()
        listenForMsgEvent()
        listForChangeInProfileIdEvent()
        listenForSaveDoneEvent()
        listenForDeleteDoneEvent()
        listenForCancelEvent()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.editprofile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        btnDelete = menu?.findItem(R.id.btnDelete)

        setBtnDeleteVisibility()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        if (item.itemId == R.id.btnDelete) {
            editProfileViewModel.deleteProfile()
        }

        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (permissionHelper.isExternalStoragePermissionGranted(requestCode, permissions, grantResults)) {
            launchChoosePhotoIntent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (photoChooser.isValidActivityResult(requestCode, resultCode, data)) {
            data?.data?.let { inData -> editProfileViewModel.copyUserProfilePhoto(inData) }
        }
    }

    private fun loadViewModel() {
        editProfileViewModel = ViewModelProviders.of(this, viewModelFactory).get(EditProfileViewModel::class.java)
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

    private fun listForChangeInProfileIdEvent() {
        editProfileViewModel.profileLiveData.observe(this, Observer<Profile> { setBtnDeleteVisibility() })
    }


    private fun listenForChoosePhotoEvent() {
        editProfileViewModel.choosePhotoEvent.observe(this, Observer {
            if (permissionHelper.isGrantedReadStoragePermission()) {
                launchChoosePhotoIntent()
            } else {
                permissionHelper.askForStoragePermission()
            }
        })
    }


    private fun listenForMsgEvent() {
        editProfileViewModel.msgEvent.observe(this, Observer { msg ->
            msg?.let { snackBarMsgDisplayer.displayMsg(it) }
        })
    }

    private fun listenForSaveDoneEvent() {
        editProfileViewModel.saveDoneEvent.observe(this, Observer { profileId ->
            profileId?.let { inProfileId -> navigator.goBackToViewProfile(inProfileId) }
        })
    }

    private fun listenForDeleteDoneEvent() {
        editProfileViewModel.deleteDoneEvent.observe(this, Observer {
            navigator.goBackToListProfiles()
        })
    }

    private fun listenForCancelEvent() {
        editProfileViewModel.cancelEvent.observe(this, Observer {
            navigator.goBack()
        })
    }


    private fun launchChoosePhotoIntent() {
        photoChooser.launchChoosePhotoIntent()
    }

    private fun setBtnDeleteVisibility() {
        btnDelete?.isVisible = editProfileViewModel.isDeleteBtnVisible

    }
}


