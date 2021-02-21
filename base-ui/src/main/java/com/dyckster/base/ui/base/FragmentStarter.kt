package com.dyckster.base.ui.base

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

private const val BF_INIT_PARAMS = ".init.params"

abstract class FragmentInitializer {
    open fun defaultTag(): String = fragmentClass().simpleName

    abstract fun fragmentClass(): Class<*>

    open fun newInstance(initParams: InitParams? = null): Fragment {
        return FragmentFactory().instantiate(fragmentClass().classLoader!!, fragmentClass().name)
            .apply {
                arguments = Bundle().apply {
                    initParams?.let { putParcelable(BF_INIT_PARAMS, it) }
                }
            }
    }
}

abstract class DialogFragmentStarter : FragmentInitializer() {

    override fun newInstance(initParams: InitParams?): DialogFragment {
        return super.newInstance(initParams) as DialogFragment
    }
}

fun <T : InitParams> Fragment.extractInitParams(): T {
    return extractInitParams(arguments)
}

fun <T : InitParams> extractInitParams(arguments: Bundle?): T {
    val initParams = arguments?.getParcelable<T>(BF_INIT_PARAMS)
    if (initParams == null) {
        throw IllegalStateException("initParams should not be null")
    } else {
        return initParams
    }
}
