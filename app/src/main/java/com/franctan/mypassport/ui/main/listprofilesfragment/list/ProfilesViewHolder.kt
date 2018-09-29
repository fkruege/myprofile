package com.franctan.mypassport.ui.main.listprofilesfragment.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.franctan.models.Profile
import com.franctan.mypassport.R
import com.franctan.mypassport.ui.main.listprofilesfragment.ProfileClickListener
import kotlinx.android.synthetic.main.card_profile.view.*


class ProfilesViewHolder(
        private val view: View,
        private val clickListener: ProfileClickListener)
    : RecyclerView.ViewHolder(view) {

    fun bind(model: Profile) {

        loadImage(model.profilePhotoPath)
        view.txtName.text = "${model.firstName} ${model.lastName}"

        addClickListenerToView(view.card_view, model)
        addClickListenerToView(view.mainLayout, model)
        addClickListenerToView(view.imgProfile, model)
        addClickListenerToView(view.txtName, model)
    }

    private fun loadImage(imagePath: String) {
        if (imagePath.isEmpty()) {
            view.imgProfile.setImageResource(R.drawable.ic_person_outline)
        } else {
            Glide.with(view.context)
                    .load(imagePath)
                    .into(view.imgProfile)
        }

    }

    private fun addClickListenerToView(view: View, profile: Profile) {
        view.setOnClickListener { clickListener.clicked(profile) }
    }


}