package com.dyckster.base.ui.list.screen

import com.dyckster.base.ui.chart.screen.BitcoinChartInitParams
import com.dyckster.base.ui.list.mapper.ChartTypeUiMapper
import com.dyckster.base.ui.list.model.ChartTypeUiModel
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.usecase.BitcoinChartTypeListGetterUseCase
import com.dyckster.utils.TrampolineSchedulerExtension
import com.dyckster.utils.mockkRelaxed
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TrampolineSchedulerExtension::class)
internal class BitcoinChartListPresenterTest {

    private val chartTypeUiMapper = mockkRelaxed<ChartTypeUiMapper>()
    private val bitcoinChartTypeListGetterUseCase =
        mockkRelaxed<BitcoinChartTypeListGetterUseCase>()

    private val presenter = BitcoinChartListPresenter(
        chartTypeUiMapper = chartTypeUiMapper,
        bitcoinChartTypeListGetterUseCase = bitcoinChartTypeListGetterUseCase
    )

    private val viewState = mockkRelaxed<`BitcoinChartListView$$State`>()

    @BeforeEach
    private fun setup() {
        presenter.setViewState(viewState)
    }

    @Test
    fun `should load and show chart types`() {
        val chartType = mockkRelaxed<ChartType>()
        val chartTypeUiModel = mockkRelaxed<ChartTypeUiModel>()
        val types = listOf(chartType)
        val uiModelTypes = listOf(chartTypeUiModel)
        every { chartTypeUiMapper.map(chartType) } returns chartTypeUiModel
        every { bitcoinChartTypeListGetterUseCase.getChartTypes() } returns types

        presenter.attachView(mockkRelaxed())

        verify(exactly = 1) {
            chartTypeUiMapper.map(chartType)
            bitcoinChartTypeListGetterUseCase.getChartTypes()
            viewState.showTypes(uiModelTypes)
        }
    }

    @Test
    fun `should open chart screen on type click`() {
        val chartType = ChartType.TRANSACTION_NUM
        val chartTypeUiModel = ChartTypeUiModel("name")
        val types = listOf(chartType)
        every { chartTypeUiMapper.map(chartType) } returns chartTypeUiModel
        every { bitcoinChartTypeListGetterUseCase.getChartTypes() } returns types
        val expectedInitParams = BitcoinChartInitParams(
            title = "name",
            chartType = ChartType.TRANSACTION_NUM
        )

        presenter.attachView(mockkRelaxed())
        chartTypeUiModel.onClickAction?.invoke()

        verify(exactly = 1) {
            viewState.openChart(expectedInitParams)
        }
    }
}