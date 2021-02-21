package com.dyckster.base.ui.list.screen

import com.dyckster.base.ui.base.BasePresenter
import com.dyckster.base.ui.chart.screen.BitcoinChartInitParams
import com.dyckster.base.ui.list.mapper.ChartTypeUiMapper
import com.dyckster.base.ui.list.model.ChartTypeUiModel
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.usecase.BitcoinChartTypeListGetterUseCase
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class BitcoinChartListPresenter
@Inject constructor(
    private val chartTypeUiMapper: ChartTypeUiMapper,
    private val bitcoinChartTypeListGetterUseCase: BitcoinChartTypeListGetterUseCase
) : BasePresenter<BitcoinChartListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCategories()
    }

    private fun loadCategories() {
        val chartTypes = bitcoinChartTypeListGetterUseCase.getChartTypes()
        val chartUiModels = mapUiModels(chartTypes)

        viewState.showTypes(chartUiModels)
    }

    private fun mapUiModels(chartTypes: List<ChartType>): List<ChartTypeUiModel> {
        return chartTypes.map { chartType ->
            val uiModel = chartTypeUiMapper.map(chartType)
            uiModel.onClickAction = {
                val initParams = BitcoinChartInitParams(
                    title = uiModel.chartName,
                    chartType = chartType
                )
                viewState.openChart(initParams)
            }
            uiModel
        }
    }
}