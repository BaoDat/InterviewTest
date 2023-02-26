package com.datdang.interviewtest.extension

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("errorText")
fun bindErrorText(view: TextView, strOrResId: Any?) {
    if (strOrResId == null) {
        return
    }
    if (strOrResId is Int) {
        view.text = view.context.getString(strOrResId)
    } else {
        view.text = strOrResId as String
    }
}

@BindingAdapter("visible")
fun showHideView(view: TextView, show: Boolean) {
    if (show) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}