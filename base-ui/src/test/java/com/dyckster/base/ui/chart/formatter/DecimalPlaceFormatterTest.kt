package com.dyckster.base.ui.chart.formatter

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.tableOf
import org.junit.jupiter.api.Test

internal class DecimalPlaceFormatterTest {

    private val formatter = DecimalPlaceFormatter()

    @Test
    fun `should format`() {
        tableOf("value", "decimalPlace", "expected")
            .row(100.12345, 2, 100.12)
            .row(100.12345, 1, 100.1)
            .row(100.12345, 3, 100.123)
            .forAll { value, decimalPlace, expected ->
                val actual = formatter.format(value, decimalPlace)

                assertThat(actual).isEqualTo(expected)
            }
    }
}