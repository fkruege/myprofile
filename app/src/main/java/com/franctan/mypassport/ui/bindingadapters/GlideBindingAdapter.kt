package com.franctan.mypassport.ui.bindingadapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.franctan.mypassport.R
import timber.log.Timber


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    Timber.d("Load with glide $url")

    if (url.isNullOrEmpty()) {
        imageView.setImageResource(R.drawable.ic_person_outline)
    } else {
        Glide.with(imageView.context)
                .load(url)
                .transition(withCrossFade())
                .into(imageView)
    }

}