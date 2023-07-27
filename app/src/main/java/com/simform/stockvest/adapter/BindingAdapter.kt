package com.simform.stockvest.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:isActivated")
fun View.isActivated(activated: Boolean) {
    isActivated = activated
}
