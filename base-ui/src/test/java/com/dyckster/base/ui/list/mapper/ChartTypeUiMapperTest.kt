package com.dyckster.base.ui.list.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.tableOf
import com.dyckster.base.ui.R
import com.dyckster.base.ui.list.model.ChartTypeUiModel
import com.dyckster.base.ui.util.ResourcesWrapper
import com.dyckster.domain.model.ChartType
import com.dyckster.utils.mockkRelaxed
import io.mockk.every
import org.junit.jupiter.api.Test

internal class ChartTypeUiMapperTest {

    private val resourcesWrapper = mockkRelaxed<ResourcesWrapper> {
        every { getString(R.string.chart_type_circulation) } returns "circ"
        every { getString(R.string.chart_type_trade_volume) } returns "volume"
        every { getString(R.string.chart_type_market_price) } returns "price"
        every { getString(R.string.chart_type_market_cap) } returns "cap"
        every { getString(R.string.chart_type_transaction_num) } returns "transactions"
    }
    private val mapper = ChartTypeUiMapper(
        resourcesWrapper = resourcesWrapper
    )

    @Test
    fun `should map`() {
        tableOf("chartType", "expectedName")
            .row(ChartType.BITCOIN_CIRCULATION, "circ")
            .row(ChartType.MARKET_PRICE, "price")
            .row(ChartType.MARKET_CAP, "cap")
            .row(ChartType.TRADE_VOLUME, "volume")
            .row(ChartType.TRANSACTION_NUM, "transactions")
            .forAll { chartType, expectedName ->
                val expected = ChartTypeUiModel(expectedName)
                val actual = mapper.map(chartType)

                assertThat(actual).isEqualTo(expected)
            }
    }
}