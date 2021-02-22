package com.dyckster.base.ui.chart.formatter

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.dyckster.utils.mockkRelaxed
import io.mockk.every
import org.junit.jupiter.api.Test

internal class NumberPeriodsFormatterTest {

    private val decimalPlaceFormatter = mockkRelaxed<DecimalPlaceFormatter>()
    private val formatter = NumberPeriodsFormatter(
        decimalPlaceFormatter = decimalPlaceFormatter
    )

    @Test
    fun `should map for billions`() {
        val input = 1_000_000_012L
        val expectedDivisionResult = 1.000000012
        every { decimalPlaceFormatter.format(expectedDivisionResult, 2) } returns 1.0
        val expected = "1b"

        val actual = formatter.format(input)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should map for millions`() {
        val input = 1123000L
        val expectedDivisionResult = 1.123000
        every {
            decimalPlaceFormatter.format(expectedDivisionResult, 2)
        } returns 1.12
        val expected = "1.12m"

        val actual = formatter.format(input)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should map for thousands`() {
        val input = 15000L
        val expectedDivisionResult = 15.0
        every {
            decimalPlaceFormatter.format(expectedDivisionResult, 2)
        } returns 15.0
        val expected = "15k"

        val actual = formatter.format(input)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should map normally`() {
        val input = 100L
        val expectedDivisionResult = 100.0
        every {
            decimalPlaceFormatter.format(expectedDivisionResult, 2)
        } returns 100.0
        val expected = "100"

        val actual = formatter.format(input)

        assertThat(actual).isEqualTo(expected)
    }
}