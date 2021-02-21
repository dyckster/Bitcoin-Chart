package com.dyckster.base.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes layoutRes: Int) : MvpAppCompatActivity(layoutRes),
    HasAndroidInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return childFragmentInjector
    }
}