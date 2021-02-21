package com.dyckster.data.repository.impl

import com.dyckster.data.mapper.ChartQueryRequestMapper
import com.dyckster.data.mapper.ChartResponseMapper
import com.dyckster.data.repository.BitcoinChartRepository
import com.dyckster.data.source.api.service.BitcoinService
import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.model.Timespan
import io.reactivex.Single
import javax.inject.Inject

class BitcoinChartRepositoryImpl
@Inject constructor(
    private val chartQueryRequestMapper: ChartQueryRequestMapper,
    private val chartResponseMapper: ChartResponseMapper,
    private val service: BitcoinService
) : BitcoinChartRepository {

    override fun getChart(chartType: ChartType, timespan: Timespan): Single<ChartModel> {
        val url = chartQueryRequestMapper.mapUrl(chartType)
        val timespanQuery = chartQueryRequestMapper.maptimeSpan(timespan)

        return service.getChart(url, timespanQuery)
            .map { chartResponse ->
                chartResponseMapper.map(chartResponse)
            }
    }

    override fun getChartTypes(): List<ChartType> {
        return ChartType.values().toList()
    }
}