package com.dyckster.base.ui.chart.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.dyckster.base.ui.R
import com.dyckster.base.ui.chart.model.ChartEntry
import com.dyckster.base.ui.chart.model.ChartUiModel
import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.ChartValueModel
import com.dyckster.domain.model.Timespan
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class ChartUiMapperTest {

    private val mapper = ChartUiMapper()

    @Test
    fun `should map`() {
        val inputChartValue = ChartValueModel(
            date = 1613941392983,
            value = BigDecimal("1.767250129E11")
        )
        val expectedEntry = ChartEntry(
            date = 1613941392983,
            value = BigDecimal("1.767250129E11")
        )
        val inputModel = ChartModel(
            name = "name",
            unit = "unit",
            description = "description",
            period = "period",
            values = listOf(inputChartValue)
        )
        val expected = ChartUiModel(
            chartEntries = listOf(expectedEntry),
            chartLineWidth = 2.5f,
            chartColor = R.color.colorPrimary,
            timespan = Timespan.YEAR,
            chartDescription = "description"
        )

        val actual = mapper.map(inputModel, Timespan.YEAR)

        assertThat(actual).isEqualTo(expected)
    }
}