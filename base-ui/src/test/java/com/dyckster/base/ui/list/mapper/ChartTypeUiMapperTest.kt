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
        every { getString(R.string.chart_type_title_circulation) } returns "circ"
        every { getString(R.string.chart_type_title_trade_volume) } returns "volume"
        every { getString(R.string.chart_type_title_market_price) } returns "price"
        every { getString(R.string.chart_type_title_market_cap) } returns "cap"
        every { getString(R.string.chart_type_title_transaction_num) } returns "transactions"

        every { getString(R.string.chart_type_description_circulation) } returns "circ desc"
        every { getString(R.string.chart_type_description_trade_volume) } returns "volume desc"
        every { getString(R.string.chart_type_description_market_price) } returns "price desc"
        every { getString(R.string.chart_type_description_market_cap) } returns "cap desc"
        every {
            getString(R.string.chart_type_description_transaction_num)
        } returns "transactions desc"
    }
    private val mapper = ChartTypeUiMapper(
        resourcesWrapper = resourcesWrapper
    )

    @Test
    fun `should map`() {
        tableOf("chartType", "expectedName", "expectedDescription")
            .row(ChartType.BITCOIN_CIRCULATION, "circ", "circ desc")
            .row(ChartType.MARKET_PRICE, "price", "price desc")
            .row(ChartType.MARKET_CAP, "cap", "cap desc")
            .row(ChartType.TRADE_VOLUME, "volume", "volume desc")
            .row(ChartType.TRANSACTION_NUM, "transactions", "transactions desc")
            .forAll { chartType, expectedName, expectedDescription ->
                val expected = ChartTypeUiModel(expectedName, expectedDescription)
                val actual = mapper.map(chartType)

                assertThat(actual).isEqualTo(expected)
            }
    }
}