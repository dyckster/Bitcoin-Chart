package com.dyckster.base.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent

private const val BF_INIT_PARAMS = ".init.params"

abstract class ActivityStarter {

    abstract fun activityClass(): Class<*>

    private fun instance(context: Context, initParams: InitParams? = null): Intent {
        val intent = Intent(context, activityClass())
        initParams?.let { intent.putExtra(BF_INIT_PARAMS, it) }
        return intent
    }

    fun start(context: Context, initParams: InitParams? = null) {
        context.startActivity(instance(context, initParams))
    }
}

fun <T : InitParams> Activity.extractInitParams(): T {
    return extractInitParams(intent)
}

fun <T : InitParams> extractInitParams(intent: Intent): T {
    val initParams = intent.getParcelableExtra<T>(BF_INIT_PARAMS)
    if (initParams == null) {
        throw IllegalStateException("initParams should not be null")
    } else {
        return initParams
    }
}