package com.franctan.mypassport.ui.bindingadapters

import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.franctan.models.Gender
import com.franctan.mypassport.R

@BindingAdapter("genderBackgroundColor")
fun View.setGenderBackgroundColor(gender: Gender) {
    if (gender == Gender.Male) {
        this.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorBlue))
    } else {
        this.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorPink))
    }
}

@BindingAdapter("genderAsString")
fun EditText.setGenderAsString(gender: Gender) {
    this.setText(gender.toString())
}

@BindingAdapter("isMaleCheckedGender")
fun RadioButton.setIsMaleCheckedGender(gender: Gender) {
    this.isChecked = gender == Gender.Male

}

@BindingAdapter("isFemaleCheckedGender")
fun RadioButton.setIsFemaleCheckedGender(gender: Gender) {
    this.isChecked = gender == Gender.Female

}




