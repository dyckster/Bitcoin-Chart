package com.dyckster.base.ui.list.mapper

import com.dyckster.base.ui.R
import com.dyckster.base.ui.list.model.ChartTypeUiModel
import com.dyckster.base.ui.util.ResourcesWrapper
import com.dyckster.domain.model.ChartType
import javax.inject.Inject

class ChartTypeUiMapper
@Inject constructor(
    private val resourcesWrapper: ResourcesWrapper
) {

    fun map(chartType: ChartType): ChartTypeUiModel {
        val chartName = createChartName(chartType)
        val chartDescription = createChartDescription(chartType)
        return ChartTypeUiModel(
            chartName = chartName,
            chartDescription = chartDescription
        )
    }

    private fun createChartName(chartType: ChartType): String {
        return when (chartType) {
            ChartType.BITCOIN_CIRCULATION -> {
                resourcesWrapper.getString(R.string.chart_type_title_circulation)
            }
            ChartType.TRADE_VOLUME -> {
                resourcesWrapper.getString(R.string.chart_type_title_trade_volume)
            }
            ChartType.MARKET_PRICE -> {
                resourcesWrapper.getString(R.string.chart_type_title_market_price)
            }
            ChartType.MARKET_CAP -> {
                resourcesWrapper.getString(R.string.chart_type_title_market_cap)
            }
            ChartType.TRANSACTION_NUM -> {
                resourcesWrapper.getString(R.string.chart_type_title_transaction_num)
            }
        }
    }

    private fun createChartDescription(chartType: ChartType): String {
        return when (chartType) {
            ChartType.BITCOIN_CIRCULATION -> {
                resourcesWrapper.getString(R.string.chart_type_description_circulation)
            }
            ChartType.TRADE_VOLUME -> {
                resourcesWrapper.getString(R.string.chart_type_description_trade_volume)
            }
            ChartType.MARKET_PRICE -> {
                resourcesWrapper.getString(R.string.chart_type_description_market_price)
            }
            ChartType.MARKET_CAP -> {
                resourcesWrapper.getString(R.string.chart_type_description_market_cap)
            }
            ChartType.TRANSACTION_NUM -> {
                resourcesWrapper.getString(R.string.chart_type_description_transaction_num)
            }
        }
    }
}