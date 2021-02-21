package com.dyckster.data.di

import com.dyckster.data.repository.BitcoinChartRepository
import com.dyckster.data.repository.impl.BitcoinChartRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryProvider {

    @Binds
    fun provideBitcoinChartRepository(impl: BitcoinChartRepositoryImpl): BitcoinChartRepository
}