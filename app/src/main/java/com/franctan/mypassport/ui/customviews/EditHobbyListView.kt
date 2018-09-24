package com.franctan.mypassport.ui.customviews

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.franctan.mypassport.R
import kotlinx.android.synthetic.main.view_hobbies_edit.view.*

class EditHobbyListView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private lateinit var hobbiesList: MutableList<String>

    init {
        LayoutInflater.from(context).inflate(R.layout.view_hobbies_edit, this, true)

        txtAddMoreHobbies.setOnClickListener { addMoreClicked() }


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

    private fun addMoreClicked() {
        val newHobby = ""
        hobbiesList.add(newHobby)
        val index = hobbiesList.size - 1

        addNewRow(newHobby, index)
    }

    private fun addNewRow(hobby: String, index: Int) {
        val newHobbyRow = LayoutInflater.from(context).inflate(R.layout.item_hobby_edit, this, false)
        val imgDelete: ImageView = newHobbyRow.findViewById(R.id.imgDelete)
        val editTextHobby: TextInputEditText = newHobbyRow.findViewById(R.id.etHobby)

        imgDelete.setOnClickListener { removeHobby(index) }
        addTextWatchListener(editTextHobby, index)

        editTextHobby.setText(hobby)

        hobbyListLayout.addView(newHobbyRow)
    }

    private fun removeHobby(index: Int) {
        hobbiesList.removeAt(index)
        hobbyListLayout.removeViewAt(index)
        reinitViewListeners()
    }

    private fun reinitViewListeners() {
        for (i in 0 until hobbyListLayout.childCount) {
            val childView = hobbyListLayout.getChildAt(i)
            val imgDelete: ImageView = childView.findViewById(R.id.imgDelete)
            val editTextHobby: TextInputEditText = childView.findViewById(R.id.etHobby)

            imgDelete.setOnClickListener { removeHobby(i) }
            addTextWatchListener(editTextHobby, i)
        }
    }

    private fun addTextWatchListener(editTextHobby: TextInputEditText, index: Int) {
        removeExistingTextWatcher(editTextHobby)

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(textValue: Editable?) {
                hobbiesList[index] = textValue.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }

        editTextHobby.tag = textWatcher
        editTextHobby.addTextChangedListener(textWatcher)
    }

    private fun removeExistingTextWatcher(editTextHobby: TextInputEditText) {
        editTextHobby.tag?.let { textWatcher ->
            if (textWatcher is TextWatcher) {
                editTextHobby.removeTextChangedListener(textWatcher)
                editTextHobby.tag = null
            }
        }
    }

}


