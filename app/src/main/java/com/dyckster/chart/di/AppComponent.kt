package com.dyckster.chart.di

import com.dyckster.base.ui.di.ScreenBuilderProvider
import com.dyckster.chart.application.BitcoinChartApp
import com.dyckster.chart.di.modules.ApplicationModule
import com.dyckster.data.di.NetworkProvider
import com.dyckster.data.di.RepositoryProvider
import com.dyckster.data.di.UseCaseProvider
import com.dyckster.domain.di.PerApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ScreenBuilderProvider::class,
        ApplicationModule::class,
        UseCaseProvider::class,
        NetworkProvider::class,
        RepositoryProvider::class
    ]
)
interface AppComponent : AndroidInjector<BitcoinChartApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<BitcoinChartApp>
}