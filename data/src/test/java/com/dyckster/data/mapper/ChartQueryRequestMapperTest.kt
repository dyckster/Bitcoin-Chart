package com.dyckster.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.tableOf
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.model.Timespan
import org.junit.jupiter.api.Test

internal class ChartQueryRequestMapperTest {

    private val mapper = ChartQueryRequestMapper()

    @Test
    fun `should map url`() {
        tableOf("chartType", "chartTypeQuery")
            .row(ChartType.BITCOIN_CIRCULATION, "total-bitcoins")
            .row(ChartType.MARKET_PRICE, "market-price")
            .row(ChartType.MARKET_CAP, "market-cap")
            .row(ChartType.TRADE_VOLUME, "trade-volume")
            .row(ChartType.TRANSACTION_NUM, "n-transactions-total")
            .forAll { input, expected ->
                val actual = mapper.mapUrl(input)

                assertThat(actual).isEqualTo(expected)
            }
    }

    @Test
    fun `should map timespan`() {
        tableOf("timeSpan", "timespanQuery")
            .row(Timespan.WEEK, "1weeks")
            .row(Timespan.MONTH, "1months")
            .row(Timespan.YEAR, "1years")
            .row(Timespan.YEAR_3, "3years")
            .row(Timespan.YEAR_5, "5years")
            .forAll { timespan, expected ->
                val actual = mapper.maptimeSpan(timespan)

                assertThat(actual).isEqualTo(expected)
            }
    }
}