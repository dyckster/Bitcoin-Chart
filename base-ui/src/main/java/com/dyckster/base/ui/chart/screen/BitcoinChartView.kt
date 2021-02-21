package com.dyckster.base.ui.chart.screen

import com.dyckster.base.ui.chart.model.ChartUiModel
import com.dyckster.base.ui.chart.model.SelectedValueUiModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BitcoinChartView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setScreenTitle(title: String)

    @StateStrategyType(SkipStrategy::class)
    fun animateDragTip()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setChartData(chartModel: ChartUiModel)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSelectedValue(value: SelectedValueUiModel)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideSelectedValue()

    @StateStrategyType(SkipStrategy::class)
    fun showProgress()

    @StateStrategyType(SkipStrategy::class)
    fun hideProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(errorDescription: Int)
}