package com.franctan.mypassport.ui.main.listprofilesfragment.list

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.franctan.models.Profile
import kotlinx.android.synthetic.main.card_profile.view.*
import timber.log.Timber


class ProfilesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(model: Profile) {

        Glide.with(view.context)
                .load(model.profilePhotoPath)
//                .transition(withCrossFade())
                .into(view.imgProfile)
        view.imgProfile.avatarBackgroundColor = Color.BLUE

        Timber.d("Binding ${model.firstName} ${model.profilePhotoPath} ")

        view.txtName.text = "${model.firstName} ${model.lastName}"
    }


}