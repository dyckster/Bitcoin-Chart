package com.dyckster.base.ui.list.screen

import com.dyckster.base.ui.chart.screen.BitcoinChartInitParams
import com.dyckster.base.ui.list.model.ChartTypeUiModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BitcoinChartListView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTypes(types: List<ChartTypeUiModel>)

    @StateStrategyType(SkipStrategy::class)
    fun openChart(initParams: BitcoinChartInitParams)
}