package com.franctan.mypassport.ui.main.editprofilefragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.franctan.firebaserepo.daos.ProfilesDao
import com.franctan.mypassport.ui.models.UIProfileModel
import com.franctan.mypassport.ui.photos.PhotoCopier
import com.franctan.mypassport.ui.viewmodelsupport.SingleLiveEvent
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class EditProfileViewModel
@Inject constructor(
        private val profilesDao: ProfilesDao,
        private val photoCopier: PhotoCopier
) : ViewModel() {

    val uiProfile = UIProfileModel()
    private val choosePhotoEvent = SingleLiveEvent<Void>()
//    private val requestExternalStoragePermissionEvent = SingleLiveEvent<Void>()

    private val imagePathLiveData: MutableLiveData<String> = MutableLiveData()

    fun choosePhotoListener(): SingleLiveEvent<Void> {
        return choosePhotoEvent
    }

    fun imagePathLiveDataListner(): LiveData<String> {
        return imagePathLiveData
    }

    fun choosePhoto() {
        choosePhotoEvent.call()
    }

    fun copyUserProfilePhoto(uri: Uri) {
        photoCopier.copyPhoto(uri)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SingleObserver<String>{
                    override fun onSuccess(t: String) {
                        imagePathLiveData.postValue(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                    }
                })

    }


}