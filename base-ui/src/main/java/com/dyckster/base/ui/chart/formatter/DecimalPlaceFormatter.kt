package com.dyckster.base.ui.chart.formatter

import java.math.BigDecimal
import javax.inject.Inject

class DecimalPlaceFormatter
@Inject constructor() {

    fun format(double: Double, decimalPlace: Int): Double {
        var bd = BigDecimal(double)
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP)
        return bd.toDouble()
    }
}