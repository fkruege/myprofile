package com.franctan.mypassport.ui.main.listprofilesfragment.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.franctan.models.Profile
import com.franctan.mypassport.R


class ProfilesAdapter : RecyclerView.Adapter<ProfilesViewHolder>() {

    private var profileList: List<Profile> = emptyList()

    internal fun update(profileList: List<Profile>) {
        this.profileList = profileList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_profile, parent, false)
        return ProfilesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return profileList.count()
    }

    override fun onBindViewHolder(viewHolder: ProfilesViewHolder, position: Int) {
        val profileModel = profileList[position]
        viewHolder.bind(profileModel)
    }

}