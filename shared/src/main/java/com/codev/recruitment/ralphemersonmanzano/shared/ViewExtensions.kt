package com.codev.recruitment.ralphemersonmanzano.shared

import android.view.View
import androidx.core.view.isVisible

fun View.hide() {
    if (!isVisible) return
    visibility = View.GONE
}

fun View.show() {
    if (isVisible) return
    visibility = View.VISIBLE
}