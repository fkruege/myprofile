package com.franctan.mypassport.ui.customviews

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.franctan.mypassport.R
import kotlinx.android.synthetic.main.customview_hobbies_listview.view.*

class ViewHobbiesListView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private lateinit var hobbiesList: MutableList<String>

    init {
        LayoutInflater.from(context).inflate(R.layout.customview_hobbies_listview, this, true)
        orientation = VERTICAL
    }


    fun getHobbies(): MutableList<String> {
        return hobbiesList
    }

    fun setHobbies(hobbiesList: MutableList<String>) {
        this.hobbiesList = hobbiesList
        initHobbyList()
    }

    private fun initHobbyList() {

        hobbyListLayout.removeAllViews()

        var index = 0
        for (hobby in hobbiesList) {
            addNewRow(hobby, index)
            index++
        }

    }

    private fun addNewRow(hobby: String, index: Int) {
        val newHobbyRow = LayoutInflater.from(context).inflate(R.layout.item_hobby_view, this, false)
        val editTextHobby: TextInputEditText = newHobbyRow.findViewById(R.id.etHobby)
        editTextHobby.setText(hobby)

        hobbyListLayout.addView(newHobbyRow)
    }


}


