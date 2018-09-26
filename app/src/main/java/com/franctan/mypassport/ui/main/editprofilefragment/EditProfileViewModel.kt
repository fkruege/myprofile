package com.franctan.mypassport.ui.main.editprofilefragment

import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.franctan.firebasecloudstorage.dagger.FileStorageDao
import com.franctan.firebaserepo.daos.ProfilesDao
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
        private val profilesDao: ProfilesDao,
        private val fileStorageDao: FileStorageDao,
        private val photoCopier: PhotoCopier,
        private val uriHelper: UriHelper
) : ViewModel() {

    val uiProfile = UIProfileModel()
    private val choosePhotoEvent = SingleLiveEvent<Void>()

    private val compositeDisposable = CompositeDisposable()

    fun choosePhotoListener(): SingleLiveEvent<Void> {
        return choosePhotoEvent
    }


    fun choosePhoto() {
        choosePhotoEvent.call()
    }

    fun saveProfile() {
        val profileToSave = uiProfile.mapToProfile()

        fileStorageDao.upload(uiProfile.profilePhotoPath)
                .flatMap { uploadUriResult ->
                    val copy = profileToSave.copy(profilePhotoPath = uploadUriResult.toString())
                    profilesDao.saveProfile(copy)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Profile> {
                    override fun onSuccess(profile: Profile) {
                        Timber.d("Saved profile: $profile")
                    }

                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                    }
                })
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
}