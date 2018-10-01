package com.franctan.mypassport.ui.main.filterdialogfragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.franctan.mypassport.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SortDialogFragment : DialogFragment() {

    @Inject
    lateinit var sortDelegagate : SortDialogDelegate

    companion object {
        fun newInstance(): SortDialogFragment {
            return SortDialogFragment()
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val choices = sortDelegagate.getChoices()
        val selectedIndex = sortDelegagate.getSelectedIndex(choices)
        var userSelected = selectedIndex

        return AlertDialog
                .Builder(activity!!)
                .setTitle(activity?.getString(R.string.sort_by))
                .setSingleChoiceItems(choices, selectedIndex) { dialog, which -> userSelected = which }
                .setPositiveButton(resources.getString(R.string.apply)) { dialog, _ ->
                    sortDelegagate.updatePreferences(choices, userSelected)
                    dialog?.dismiss()
                }
                .create()

    }
}



