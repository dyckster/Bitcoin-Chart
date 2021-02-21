package com.dyckster.base.ui.chart.screen

import com.dyckster.base.ui.base.InitParams
import com.dyckster.domain.model.ChartType
import kotlinx.parcelize.Parcelize

@Parcelize
data class BitcoinChartInitParams(
    val title: String,
    val chartType: ChartType
) : InitParams