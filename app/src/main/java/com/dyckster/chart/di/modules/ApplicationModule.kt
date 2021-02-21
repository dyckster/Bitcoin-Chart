package com.dyckster.chart.di.modules

import android.content.Context
import android.content.res.Resources
import com.dyckster.base.ui.util.ResourcesWrapper
import com.dyckster.base.ui.util.ResourcesWrapperImpl
import com.dyckster.chart.application.BitcoinChartApp
import com.dyckster.domain.di.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun provideApplicationContext(application: BitcoinChartApp): Context {
        return application
    }

    @Provides
    @PerApplication
    fun provideResources(context: Context): Resources {
        return context.resources
    }

    @Provides
    @PerApplication
    fun provideResourcesWrapper(resources: Resources): ResourcesWrapper {
        return ResourcesWrapperImpl(resources)
    }
}