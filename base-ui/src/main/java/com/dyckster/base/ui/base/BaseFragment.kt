package com.dyckster.base.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment

abstract class BaseFragment(@LayoutRes layoutRes: Int) : MvpAppCompatFragment(layoutRes) {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}