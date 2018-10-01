package com.franctan.mypassport.ui.main.filterdialogfragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.franctan.mypassport.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FilterDialogFragment : DialogFragment() {


    @Inject
    lateinit var filterDelegate: FilterDialogDelegate

    companion object {
        fun newInstance(): FilterDialogFragment {
            return FilterDialogFragment()
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val choices = filterDelegate.getChoices()
        val selectedIndex = filterDelegate.getSelectedIndex(choices)
        var userSelected = selectedIndex

        return AlertDialog
                .Builder(activity!!)
                .setTitle(activity?.getString(R.string.filter_by))
                .setSingleChoiceItems(choices, selectedIndex) { dialog, which -> userSelected = which }
                .setPositiveButton(resources.getString(R.string.apply)) { dialog, _ ->
                    filterDelegate.updatePreferences(choices, userSelected)
                    dialog?.dismiss()
                }
                .create()

    }

}



