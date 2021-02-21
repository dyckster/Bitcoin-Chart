package com.dyckster.base.ui.util

import androidx.annotation.StringRes

interface ResourcesWrapper {

    fun getString(@StringRes res: Int): String
}