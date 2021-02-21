package com.dyckster.base.ui.di

import com.dyckster.base.ui.chart.screen.BitcoinChartActivity
import com.dyckster.base.ui.list.screen.BitcoinChartListActivity
import com.dyckster.domain.di.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ScreenBuilderProvider {

    @PerActivity
    @ContributesAndroidInjector
    fun provideBitcoinChartActivity(): BitcoinChartActivity

    @PerActivity
    @ContributesAndroidInjector
    fun provideBitcoinChartListActivity(): BitcoinChartListActivity
}