package com.dyckster.data.interactor

import com.dyckster.data.repository.BitcoinChartRepository
import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.model.Timespan
import com.dyckster.domain.usecase.BitcoinChartGetterUseCase
import com.dyckster.domain.usecase.BitcoinChartTypeListGetterUseCase
import io.reactivex.Single
import javax.inject.Inject

class BitcoinChartInteractor
@Inject constructor(
    private val bitcoinChartRepository: BitcoinChartRepository
) : BitcoinChartGetterUseCase, BitcoinChartTypeListGetterUseCase {

    override fun getChart(chartType: ChartType, timespan: Timespan): Single<ChartModel> {
        return bitcoinChartRepository.getChart(chartType, timespan)
    }

    override fun getChartTypes(): List<ChartType> {
        return bitcoinChartRepository.getChartTypes()
    }
}