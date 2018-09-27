package com.franctan.mypassport.ui.models

import android.databinding.Bindable
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.franctan.models.Gender
import com.franctan.models.Profile
import com.franctan.mypassport.BR


class UIProfileModel : Observable {

    private val propertyChangeRegistry = PropertyChangeRegistry()

    var profileId: String = ""
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.profileId)
        }


    var firstName: String = "John"
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.firstName)
        }

    var lastName: String = "Doe"
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.lastName)
        }

    var age: Int = 100
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.age)
        }


    var gender: Gender = Gender.F
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.gender)
        }

    var hobbyList: MutableList<String> = mutableListOf("boxing", "dance", "hockey")
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.hobbyList)
        }

    var profilePhotoPath: String = ""
        @Bindable get
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.profilePhotoPath)
        }

    var isMale: Boolean = false
        @Bindable get() {
            return gender == Gender.M
        }

    var isFemale: Boolean = false
        @Bindable get() {
            return gender == Gender.F
        }

    fun setMaleClicked() {
        gender = Gender.M
    }

    fun setFemaleClicked() {
        gender = Gender.F
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    fun update(profile: Profile){
       this.profileId = profile.id
        this.firstName = profile.firstName
        this.lastName = profile.lastName
        this.age = profile.age
        this.gender = profile.gender
        this.hobbyList = profile.hobbyList.toMutableList()
        this.profilePhotoPath = profile.profilePhotoPath
    }

}