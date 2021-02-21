package com.dyckster.base.ui.chart.formatter

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.tableOf
import com.dyckster.domain.model.Timespan
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class ChartTimeFormatterTest {

    @BeforeEach
    fun setup() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        Locale.setDefault(Locale.ENGLISH)
    }

    private val formatter = ChartTimeFormatter()

    @Test
    fun `should map`() {
        val timestamp = 1613941392983f //Sun Feb 21 2021 21:03:12
        tableOf("input", "timespan", "expected")
            .row(timestamp, Timespan.WEEK, "21.02")
            .row(timestamp, Timespan.MONTH, "21.02")
            .row(timestamp, Timespan.YEAR, "Feb")
            .row(timestamp, Timespan.YEAR_3, "2021")
            .row(timestamp, Timespan.YEAR_5, "2021")
            .forAll { input, timespan, expected ->
                val actual = formatter.format(input, timespan)

                assertThat(actual).isEqualTo(expected)
            }
    }
}