package com.dyckster.base.ui.chart.screen

import com.dyckster.base.ui.R
import com.dyckster.base.ui.base.BasePresenter
import com.dyckster.base.ui.chart.mapper.ChartUiMapper
import com.dyckster.base.ui.chart.mapper.SelectedChartValueMapper
import com.dyckster.base.ui.chart.model.ChartUiModel
import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.Timespan
import com.dyckster.domain.usecase.BitcoinChartGetterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class BitcoinChartPresenter
@Inject constructor(
    private val chartUiMapper: ChartUiMapper,
    private val selectedChartValueMapper: SelectedChartValueMapper,
    private val bitcoinChartGetterUseCase: BitcoinChartGetterUseCase
) : BasePresenter<BitcoinChartView>() {

    private lateinit var initParams: BitcoinChartInitParams
    private lateinit var currentTimespan: Timespan

    fun init(initParams: BitcoinChartInitParams) {
        this.initParams = initParams
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setScreenTitle(initParams.title)
        viewState.animateDragTip()
    }

    fun onRetry() {
        changeTimespan(currentTimespan)
    }

    fun changeTimespan(timespan: Timespan) {
        this.currentTimespan = timespan
        loadChartDataForTimespan(timespan)
    }

    private fun loadChartDataForTimespan(timespan: Timespan) {
        viewState.hideSelectedValue()
        unsubscribeOnDestroy(
            bitcoinChartGetterUseCase.getChart(initParams.chartType, timespan)
                .subscribeOn(Schedulers.io())
                .map { chartModel ->
                    createUiModel(chartModel, timespan)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showProgress() }
                .doAfterTerminate { viewState.hideProgress() }
                .subscribe({ chart ->
                    viewState.setChartData(chart)
                }, { error ->
                    Timber.e(error)
                    viewState.showError(R.string.error_chart_fail)
                })
        )
    }

    private fun createUiModel(chartModel: ChartModel, timespan: Timespan): ChartUiModel {
        val uiModel = chartUiMapper.map(chartModel, timespan)
        uiModel.onChartValueSelected = { date, value ->
            val selectedValue = selectedChartValueMapper.map(
                value = value.toLong(),
                date = date,
                unit = chartModel.unit
            )
            viewState.showSelectedValue(selectedValue)
        }
        uiModel.onNoChartValueSelected = {
            viewState.hideSelectedValue()

        }

        return uiModel
    }
}