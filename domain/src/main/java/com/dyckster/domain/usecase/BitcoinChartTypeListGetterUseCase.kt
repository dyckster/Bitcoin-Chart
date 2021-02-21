package com.dyckster.domain.usecase

import com.dyckster.domain.model.ChartType

interface BitcoinChartTypeListGetterUseCase {

    fun getChartTypes(): List<ChartType>
}