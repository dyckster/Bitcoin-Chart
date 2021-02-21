package com.dyckster.data.mapper

import com.dyckster.domain.model.ChartType
import com.dyckster.domain.model.Timespan
import javax.inject.Inject

class ChartQueryRequestMapper
@Inject constructor() {

    fun mapUrl(chartType: ChartType): String {
        return when (chartType) {
            ChartType.BITCOIN_CIRCULATION -> "total-bitcoins"
            ChartType.MARKET_PRICE -> "market-price"
            ChartType.MARKET_CAP -> "market-cap"
            ChartType.TRADE_VOLUME -> "trade-volume"
            ChartType.TRANSACTION_NUM -> "n-transactions-total"
        }
    }

    fun maptimeSpan(timespan: Timespan): String {
        return when (timespan) {
            Timespan.WEEK -> "1weeks"
            Timespan.MONTH -> "1months"
            Timespan.YEAR -> "1years"
            Timespan.YEAR_3 -> "3years"
            Timespan.YEAR_5 -> "5years"
        }
    }
}