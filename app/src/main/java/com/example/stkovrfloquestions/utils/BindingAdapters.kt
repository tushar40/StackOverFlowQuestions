package com.example.stkovrfloquestions.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:makeVisible")
fun makeVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}