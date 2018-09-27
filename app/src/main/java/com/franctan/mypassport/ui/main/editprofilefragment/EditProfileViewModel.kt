package com.franctan.mypassport.ui.main.editprofilefragment

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.net.Uri
import com.franctan.models.Profile
import com.franctan.mypassport.ui.models.UIProfileModel
import com.franctan.mypassport.ui.models.mapToProfile
import com.franctan.mypassport.ui.photos.PhotoCopier
import com.franctan.mypassport.ui.viewmodelsupport.SingleLiveEvent
import com.franctan.utilities.UriHelper
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class EditProfileViewModel
@Inject constructor(
        private val profileSaver: ProfileSaver,
        private val photoCopier: PhotoCopier,
        private val validator: ProfileValidator,
        private val uriHelper: UriHelper
) : ViewModel() {

    val uiProfile = UIProfileModel()
    private val choosePhotoEvent = SingleLiveEvent<Void>()

    private val errMsgEvent = SingleLiveEvent<String>()

    private val compositeDisposable = CompositeDisposable()

    val isProgressBarVisible = ObservableBoolean(false)

    fun choosePhotoListener(): SingleLiveEvent<Void> {
        return choosePhotoEvent
    }

    fun errorMsgListener(): SingleLiveEvent<String> {
        return errMsgEvent
    }


    fun choosePhoto() {
        choosePhotoEvent.call()
    }

    fun saveProfile() {
        val profileToSave = uiProfile.mapToProfile()
        val isSaveResult = validator.isSaveValid(profileToSave)

        if (isSaveResult.isValid) {
            profileSaver.saveProfile(profileToSave)
                    .subscribe(object : SingleObserver<Profile> {
                        override fun onSuccess(profile: Profile) {
                            Timber.d("Saved profile: $profile")
                            isProgressBarVisible.set(false)
                            uiProfile.update(profile)
                            errMsgEvent.value = "Profile saved."
                        }

                        override fun onSubscribe(d: Disposable) {
                            compositeDisposable.add(d)
                            isProgressBarVisible.set(true)
                        }

                        override fun onError(e: Throwable) {
                            isProgressBarVisible.set(false)
                            errMsgEvent.value = "There was an error saving the profile.  Please try again."
                        }
                    })
        } else {
            errMsgEvent.value = isSaveResult.msg
        }

    }

    fun copyUserProfilePhoto(uri: Uri) {
        photoCopier.copyPhoto(uri)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<String> {
                    override fun onSuccess(profilePhotoPath: String) {
                        uiProfile.profilePhotoPath = profilePhotoPath
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun updateUiProfile(profile: Profile) {


//    val profileId = this.id
//    val firstName = this.firstName
//    val lastName = this.lastName
//    val age = this.age
//    val gender = this.gender
//    val hobbies = this.hobbyList
//    val profilePhotoPath = this.profilePhotoPath
//    val dateCreated = this.dateCreated
     }
}