package com.dyckster.base.ui.chart.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.dyckster.base.ui.chart.formatter.NumberPeriodsFormatter
import com.dyckster.base.ui.chart.model.SelectedValueUiModel
import com.dyckster.utils.mockkRelaxed
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class SelectedChartValueMapperTest {

    private val numberPeriodsFormatter = mockkRelaxed<NumberPeriodsFormatter>()

    private val mapper = SelectedChartValueMapper(
        numberPeriodsFormatter = numberPeriodsFormatter
    )

    @BeforeEach
    fun setup() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        Locale.setDefault(Locale.ENGLISH)
    }

    @Test
    fun `should map`() {
        val inputValue = 100_000_000_000L
        val date = 1613941392983f //Sun Feb 21 2021 21:03:12
        val unit = "USD"
        every { numberPeriodsFormatter.format(inputValue) } returns "100b"
        val expected = SelectedValueUiModel(
            value = "100b USD",
            date = "21 Feb 2021 21:04"
        )

        val actual = mapper.map(inputValue, date, unit)

        assertThat(actual).isEqualTo(expected)
    }
}