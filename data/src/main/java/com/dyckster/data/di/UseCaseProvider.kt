package com.dyckster.data.di

import com.dyckster.data.interactor.BitcoinChartInteractor
import com.dyckster.domain.usecase.BitcoinChartGetterUseCase
import com.dyckster.domain.usecase.BitcoinChartTypeListGetterUseCase
import dagger.Binds
import dagger.Module

@Module
interface UseCaseProvider {

    @Binds
    fun provideBitcoinChartTypeListGetterUseCase(
        impl: BitcoinChartInteractor
    ): BitcoinChartTypeListGetterUseCase

    @Binds
    fun provideBitcoinChartGetterUseCase(impl: BitcoinChartInteractor): BitcoinChartGetterUseCase
}