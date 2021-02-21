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
        val chartName = when (chartType) {
            ChartType.BITCOIN_CIRCULATION -> {
                resourcesWrapper.getString(R.string.chart_type_circulation)
            }
            ChartType.TRADE_VOLUME -> {
                resourcesWrapper.getString(R.string.chart_type_trade_volume)
            }
            ChartType.MARKET_PRICE -> {
                resourcesWrapper.getString(R.string.chart_type_market_price)
            }
            ChartType.MARKET_CAP -> {
                resourcesWrapper.getString(R.string.chart_type_market_cap)
            }
            ChartType.TRANSACTION_NUM -> {
                resourcesWrapper.getString(R.string.chart_type_transaction_num)
            }
        }
        return ChartTypeUiModel(chartName = chartName)
    }
}