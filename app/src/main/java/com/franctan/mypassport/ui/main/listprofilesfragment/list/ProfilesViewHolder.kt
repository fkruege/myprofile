package com.franctan.mypassport.ui.main.listprofilesfragment.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.franctan.models.Profile
import kotlinx.android.synthetic.main.card_profile.view.*


class ProfilesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(model: Profile) {
        view.txtName.text = "${model.firstName} ${model.lastName}"
    }


}