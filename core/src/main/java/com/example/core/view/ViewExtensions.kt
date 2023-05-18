package com.example.core.view

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun View.showKeyboard(): Boolean = context.getSystemService<InputMethodManager>()
    ?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT) ?: false