package com.dyckster.base.ui.chart.formatter

import javax.inject.Inject

private const val BILLION = 1_000_000_000
private const val BILLION_FORMAT = "%sb"
private const val MILLION = 1_000_000
private const val MILLION_FORMAT = "%sm"
private const val THOUSAND = 1_000
private const val THOUSAND_FORMAT = "%sk"
private const val NO_FORMATTING = "%s"

class NumberPeriodsFormatter
@Inject constructor(
    private val decimalPlaceFormatter: DecimalPlaceFormatter
) {

    fun format(value: Long): String {
        val numberFormat = when {
            value >= BILLION -> BILLION_FORMAT
            value >= MILLION -> MILLION_FORMAT
            value >= THOUSAND -> THOUSAND_FORMAT
            else -> NO_FORMATTING
        }
        val formatterNumber = prepareNumberForFormat(value)

        return numberFormat.format(formatterNumber)
    }

    private fun prepareNumberForFormat(value: Long): Double {
        val divisionPeriod = when {
            value >= BILLION -> BILLION
            value >= MILLION -> MILLION
            value >= THOUSAND -> THOUSAND
            else -> 1
        }

        val divisionResult = value.toDouble() / divisionPeriod.toDouble()
        return decimalPlaceFormatter.format(divisionResult, decimalPlace = 2)
    }
}