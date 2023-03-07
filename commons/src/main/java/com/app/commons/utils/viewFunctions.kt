package com.app.commons.utils

import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager

fun hideStatusBar(window: Window) {
    with(window) {
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        attributes.flags =
            window.attributes.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        statusBarColor = Color.TRANSPARENT
    }
}