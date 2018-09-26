//package com.franctan.mypassport.ui.bindingadapters
//
//import android.databinding.BindingAdapter
//import android.databinding.InverseBindingAdapter
//import android.databinding.InverseBindingListener
//import android.support.design.widget.TextInputEditText
//import android.text.Editable
//import android.text.TextWatcher
//
//
//@BindingAdapter(value = "realValueAttrChanged")
//fun setListener(editText: TextInputEditText, listener: InverseBindingListener?) {
//    if (listener != null) {
//        editText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
//
//            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
//
//            override fun afterTextChanged(editable: Editable) {
//                listener.onChange()
//            }
//        })
//    }
//}
//
//@BindingAdapter("realValue")
//fun setRealValue(view: TextInputEditText, value: Int) {
//    if (!isSameValue()) {
//        view.setText((value * 10).toString())
//    }
//}
//
//@InverseBindingAdapter(attribute = "realValue")
//fun getRealValue(editText: TextInputEditText): Int {
//    return editText.getRawValue() / 100
//}
//
//private fun isSameValue(view: TextInputEditText, value: Int): Boolean {
//
//
//}
//
//
