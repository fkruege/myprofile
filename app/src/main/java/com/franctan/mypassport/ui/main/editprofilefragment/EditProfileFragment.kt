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
import com.franctan.mypassport.ui.common.SnackBarMsgDisplayer
import com.franctan.mypassport.ui.converters.IntConverter
import com.franctan.mypassport.ui.permissions.PermissionHelper
import com.franctan.mypassport.ui.photos.PhotoChooser
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import javax.inject.Inject


class EditProfileFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var intConverter: IntConverter

    @Inject
    lateinit var permissionHelper: PermissionHelper

    @Inject
    lateinit var photoChooser: PhotoChooser

    @Inject
    lateinit var snackBarMsgDisplayer: SnackBarMsgDisplayer

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
        setHasOptionsMenu(true)

        editProfileBinding = FragmentEditProfileBinding.inflate(inflater, container, false)
        editProfileBinding.editProfileViewModel = editProfileViewModel
        editProfileBinding.intConverter = intConverter
        return editProfileBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        listenForChoosePhotoEvent()
        listenForMsgEvent()
        listForChangeInProfileIdEvent()
    }

    var btnDelete: MenuItem? = null
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
//        toolbar.inflateMenu(R.menu.editprofile_menu)
        val parentActivity: AppCompatActivity = this.activity as AppCompatActivity
        parentActivity.setSupportActionBar(toolbar)
        parentActivity.supportActionBar?.setHomeButtonEnabled(true)
        parentActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        parentActivity.menuInflater.inflate(R.menu.editprofile_menu)
    }

    private fun listForChangeInProfileIdEvent() {
        editProfileViewModel.profileLiveData.observe(this, object : Observer<Profile> {
            override fun onChanged(profile: Profile?) {
                setBtnDeleteVisibility()
            }
        })
    }


    private fun listenForChoosePhotoEvent() {
        editProfileViewModel.choosePhotoEvent.observe(this, Observer<Void> {
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

    private fun launchChoosePhotoIntent() {
        photoChooser.launchChoosePhotoIntent()
    }

    private fun setBtnDeleteVisibility() {
        btnDelete?.isVisible = editProfileViewModel.isDeleteBtnVisible

    }
}


