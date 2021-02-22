package com.dyckster.base.ui.chart.screen

import com.dyckster.base.ui.R
import com.dyckster.base.ui.chart.mapper.ChartUiMapper
import com.dyckster.base.ui.chart.mapper.SelectedChartValueMapper
import com.dyckster.base.ui.chart.model.ChartUiModel
import com.dyckster.base.ui.chart.model.SelectedValueUiModel
import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.model.Timespan
import com.dyckster.domain.usecase.BitcoinChartGetterUseCase
import com.dyckster.utils.TrampolineSchedulerExtension
import com.dyckster.utils.mockkRelaxed
import io.mockk.every
import io.mockk.verify
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TrampolineSchedulerExtension::class)
internal class BitcoinChartPresenterTest {

    private val chartUiMapper = mockkRelaxed<ChartUiMapper>()
    private val selectedChartValueMapper = mockkRelaxed<SelectedChartValueMapper>()
    private val bitcoinChartGetterUseCase = mockkRelaxed<BitcoinChartGetterUseCase>()

    private val presenter = BitcoinChartPresenter(
        chartUiMapper = chartUiMapper,
        selectedChartValueMapper = selectedChartValueMapper,
        bitcoinChartGetterUseCase = bitcoinChartGetterUseCase
    )
    private val viewState = mockkRelaxed<`BitcoinChartView$$State`>()
    private val initParams = BitcoinChartInitParams(
        title = "title",
        chartType = ChartType.TRANSACTION_NUM
    )

    @BeforeEach
    fun setup() {
        presenter.setViewState(viewState)
        presenter.init(initParams)
    }

    @Test
    fun `should set title and animate on first attach`() {
        presenter.attachView(mockkRelaxed())

        verify(exactly = 1) {
            viewState.animateDragTip()
            viewState.setScreenTitle("title")
        }
        verify(exactly = 0) {
            bitcoinChartGetterUseCase.getChart(any(), any())
        }
    }

    @Test
    fun `should load and show chart`() {
        val chartModel = mockkRelaxed<ChartModel>()
        val chartUiModel = mockkRelaxed<ChartUiModel>()
        val targetTimespan = Timespan.YEAR_5
        every {
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
        } returns Single.just(chartModel)
        every {
            chartUiMapper.map(chartModel, targetTimespan)
        } returns chartUiModel

        presenter.changeTimespan(targetTimespan)

        verify(exactly = 1) {
            viewState.hideSelectedValue()
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
            viewState.showProgress()
            viewState.hideProgress()
            viewState.setChartData(chartUiModel)
        }
    }

    @Test
    fun `should not load chart due to error`() {
        val error = Throwable()
        val targetTimespan = Timespan.YEAR_5
        every {
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
        } returns Single.error(error)

        presenter.changeTimespan(targetTimespan)

        verify(exactly = 1) {
            viewState.hideSelectedValue()
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
            viewState.showProgress()
            viewState.hideProgress()
            viewState.showError(R.string.error_chart_fail)
        }
        verify(exactly = 0) {
            viewState.setChartData(any())
        }
    }

    @Test
    fun `should retry chart request`() {
        val error = Throwable()
        val chartModel = mockkRelaxed<ChartModel>()
        val chartUiModel = mockkRelaxed<ChartUiModel>()
        val targetTimespan = Timespan.YEAR_5
        every {
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
        } returns Single.error(error) andThen Single.just(chartModel)
        every {
            chartUiMapper.map(chartModel, targetTimespan)
        } returns chartUiModel

        presenter.changeTimespan(targetTimespan)
        presenter.onRetry()

        verify(exactly = 2) {
            viewState.hideSelectedValue()
            viewState.showProgress()
            viewState.hideProgress()
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
        }
        verify(exactly = 1) {
            viewState.showError(R.string.error_chart_fail)
            viewState.setChartData(chartUiModel)
        }
    }

    @Test
    fun `should show value on select`() {
        val chartModel = mockkRelaxed<ChartModel> {
            every { unit } returns "unit"
        }
        val chartUiModel = getDefaultChartUiModel()
        val targetTimespan = Timespan.YEAR_5
        val selectedValue = mockkRelaxed<SelectedValueUiModel>()
        every {
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
        } returns Single.just(chartModel)
        every {
            chartUiMapper.map(chartModel, targetTimespan)
        } returns chartUiModel
        every {
            selectedChartValueMapper.map(10L, 10f, "unit")
        } returns selectedValue

        presenter.changeTimespan(targetTimespan)
        chartUiModel.onChartValueSelected?.invoke(10f, 10f)

        verify(exactly = 1) {
            viewState.hideSelectedValue()
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
            viewState.showProgress()
            viewState.hideProgress()
            viewState.setChartData(chartUiModel)
            viewState.showSelectedValue(selectedValue)
        }
    }

    @Test
    fun `should hide value on deselect`() {
        val chartModel = mockkRelaxed<ChartModel>()
        val chartUiModel = getDefaultChartUiModel()
        val targetTimespan = Timespan.YEAR_5
        every {
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
        } returns Single.just(chartModel)
        every {
            chartUiMapper.map(chartModel, targetTimespan)
        } returns chartUiModel

        presenter.changeTimespan(targetTimespan)
        chartUiModel.onNoChartValueSelected?.invoke()

        verify(exactly = 2) {
            viewState.hideSelectedValue()
        }
        verify(exactly = 1) {
            bitcoinChartGetterUseCase.getChart(initParams.chartType, targetTimespan)
            viewState.showProgress()
            viewState.hideProgress()
            viewState.setChartData(chartUiModel)
        }
    }

    private fun getDefaultChartUiModel(): ChartUiModel {
        return ChartUiModel(
            chartDescription = "description",
            chartColor = R.color.colorPrimary,
            chartLineWidth = 0f,
            chartEntries = emptyList(),
            timespan = Timespan.YEAR_5
        )
    }
}