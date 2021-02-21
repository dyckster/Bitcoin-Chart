package com.dyckster.data.interactor

import com.dyckster.data.repository.BitcoinChartRepository
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.model.Timespan
import com.dyckster.utils.TrampolineSchedulerExtension
import com.dyckster.utils.mockkRelaxed
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TrampolineSchedulerExtension::class)
internal class BitcoinChartInteractorTest {

    private val bitcoinChartRepository = mockkRelaxed<BitcoinChartRepository>()

    private val interactor = BitcoinChartInteractor(
        bitcoinChartRepository = bitcoinChartRepository
    )

    @Test
    fun `should get chart data`() {
        interactor.getChart(
            chartType = ChartType.TRADE_VOLUME,
            timespan = Timespan.YEAR_5
        )

        verify(exactly = 1) {
            bitcoinChartRepository.getChart(ChartType.TRADE_VOLUME, Timespan.YEAR_5)
        }
    }

    @Test
    fun `should get chart types`() {
        interactor.getChartTypes()

        verify(exactly = 1) {
            bitcoinChartRepository.getChartTypes()
        }
    }
}