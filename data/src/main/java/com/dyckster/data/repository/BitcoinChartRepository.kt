package com.dyckster.data.repository

import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.model.Timespan
import io.reactivex.Single

interface BitcoinChartRepository {

    fun getChart(chartType: ChartType, timespan: Timespan): Single<ChartModel>

    fun getChartTypes(): List<ChartType>
}