package com.dyckster.base.ui.chart.model

import androidx.annotation.ColorRes
import com.dyckster.domain.model.Timespan

data class ChartUiModel(
    val chartEntries: List<ChartEntry>,
    val chartDescription: String,
    val timespan: Timespan,
    @ColorRes
    val chartColor: Int,
    val chartLineWidth: Float
) {

    var onChartValueSelected: ((date: Float, value: Float) -> Unit)? = null
    var onNoChartValueSelected: (() -> Unit)? = null
}