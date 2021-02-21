package com.dyckster.base.ui.chart.mapper

import com.github.mikephil.charting.data.Entry
import java.math.BigDecimal

data class ChartEntry(
    val date: Long,
    val value: BigDecimal
) : Entry(
    date.toFloat(),
    value.toFloat()
)