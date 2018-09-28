package com.franctan.mypassport.ui.main.editprofilefragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.net.Uri
import com.franctan.models.Profile
import com.franctan.mypassport.ui.models.UIProfileModel
import com.franctan.mypassport.ui.models.mapToProfile
import com.franctan.mypassport.ui.photos.PhotoCopier
import com.franctan.mypassport.ui.viewmodelsupport.SingleLiveEvent
import com.franctan.utilities.IoScheduler
import com.franctan.utilities.MainScheduler
import io.reactivex.CompletableObserver
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject


class EditProfileViewModel
@Inject constructor(
        private val profileEditor: ProfileEditor,
        private val photoCopier: PhotoCopier,
        private val validator: ProfileValidator,
        @IoScheduler private val ioScheduler: Scheduler,
        @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    val uiProfile = UIProfileModel()

    internal val choosePhotoEvent = SingleLiveEvent<Void>()
    internal val msgEvent = SingleLiveEvent<String>()

    val profileLiveData = MutableLiveData<Profile>()

    private val compositeDisposable = CompositeDisposable()

    val isProgressBarVisible = ObservableBoolean(false)

    val ProfileLiveData: LiveData<Profile>
        get() = this.profileLiveData

    val isDeleteBtnVisible: Boolean
        get() = this.profileLiveData.value?.id?.isNotEmpty() == true


    init {
        val emptyProfile = Profile.EMPTY()
        profileLiveData.value = emptyProfile
        updateModels(emptyProfile)
    }

    fun choosePhoto() {
        choosePhotoEvent.call()
    }

    fun saveProfile() {
        val profileToSave = uiProfile.mapToProfile()
        val isSaveResult = validator.isSaveValid(profileToSave)

        if (isSaveResult.isValid) {
            profileEditor.saveProfile(profileToSave)
                    .subscribe(handleSaveProfileEvent())
        } else {
            msgEvent.value = isSaveResult.msg
        }
    }

    fun deleteProfile() {
        val profile = uiProfile.mapToProfile()
        profileEditor.delete(profile)
                .subscribe(handleDeleteProfileEvent())
    }


    fun copyUserProfilePhoto(uri: Uri) {
        photoCopier.copyPhoto(uri)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(handleCopyUserProfilePhotoEvent())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        Timber.d("OnCleared")
    }


    private fun handleSaveProfileEvent(): SingleObserver<Profile> {
        return object : SingleObserver<Profile> {
            override fun onSuccess(profile: Profile) {
                Timber.d("Saved profile: $profile")
                isProgressBarVisible.set(false)
                updateModels(profile)
                msgEvent.value = "Profile saved."
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
                isProgressBarVisible.set(true)
            }

            override fun onError(e: Throwable) {
                isProgressBarVisible.set(false)
                msgEvent.value = "There was an error saving the profile.  Please try again."
            }
        }
    }


    private fun handleDeleteProfileEvent(): CompletableObserver {
        return object : CompletableObserver {
            override fun onComplete() {
                msgEvent.value = "Profile deleted"
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onError(e: Throwable) {
                msgEvent.value = "There was an error deleting the profile.  Please try again."
            }
        }
    }

    private fun handleCopyUserProfilePhotoEvent(): SingleObserver<String> {
        return object : SingleObserver<String> {
            override fun onSuccess(profilePhotoPath: String) {
                uiProfile.profilePhotoPath = profilePhotoPath
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onError(e: Throwable) {
                Timber.e(e)
            }
        }
    }


    private fun updateModels(profile: Profile) {
        uiProfile.update(profile)
        profileLiveData.value = profile
    }

}