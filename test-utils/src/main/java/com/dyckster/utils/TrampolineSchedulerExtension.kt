package com.dyckster.utils

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class TrampolineSchedulerExtension : AfterEachCallback, BeforeEachCallback {

    private val trampolineScheduler = Schedulers.trampoline()

    override fun afterEach(context: ExtensionContext?) {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    override fun beforeEach(context: ExtensionContext?) {
        RxJavaPlugins.setIoSchedulerHandler { trampolineScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { trampolineScheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { trampolineScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { trampolineScheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { trampolineScheduler }
    }
}