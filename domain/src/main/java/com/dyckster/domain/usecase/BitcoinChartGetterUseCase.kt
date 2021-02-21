package com.dyckster.domain.usecase

import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.model.Timespan
import io.reactivex.Single

interface BitcoinChartGetterUseCase {

    fun getChart(chartType: ChartType, timespan: Timespan): Single<ChartModel>
}