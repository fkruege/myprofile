package com.franctan.mypassport.ui.main.listprofilesfragment.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.franctan.models.Profile
import com.franctan.mypassport.ui.main.listprofilesfragment.ProfileClickListener
import kotlinx.android.synthetic.main.card_profile.view.*
import timber.log.Timber


class ProfilesViewHolder(
        private val view: View,
        private val clickListener: ProfileClickListener)
    : RecyclerView.ViewHolder(view) {

    fun bind(model: Profile) {

        Glide.with(view.context)
//                .asBitmap()
                .load(model.profilePhotoPath)
//                .transition(withCrossFade())
                .into(view.imgProfile)

//        Glide.with(view.context)
//                .asBitmap()
//                .load(model.profilePhotoPath)
//                .centerCrop()
//                .dontAnimate()
//                .placeholder(R.drawable.placeholder_card_view)
//                .error(R.drawable.placeholder_card_view)
//                .into()
//                .into(new BitmapImageViewTarget(view.imgProfile) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                imageView.setImageDrawable(circularBitmapDrawable);
//            }
//        });

        Timber.d("Binding ${model.firstName} ${model.profilePhotoPath} ")

        view.txtName.text = "${model.firstName} ${model.lastName}"

        addClickListenerToView(view.card_view, model)
        addClickListenerToView(view.mainLayout, model)
        addClickListenerToView(view.imgProfile, model)
        addClickListenerToView(view.txtName, model)
    }

    private fun addClickListenerToView(view: View, profile: Profile) {
        view.setOnClickListener { clickListener.clicked(profile) }
    }


}