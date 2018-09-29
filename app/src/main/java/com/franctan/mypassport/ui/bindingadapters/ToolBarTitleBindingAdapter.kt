package com.franctan.mypassport.ui.bindingadapters

import android.databinding.BindingAdapter
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar

@BindingAdapter("customTitle")
fun setCustomTitle(toolbar: CollapsingToolbarLayout, title: String?) {
    title?.let {
        inTitle -> toolbar.title = inTitle
    }
}