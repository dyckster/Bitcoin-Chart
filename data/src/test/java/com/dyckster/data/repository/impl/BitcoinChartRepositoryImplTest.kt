package com.dyckster.data.repository.impl

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.dyckster.data.mapper.ChartQueryRequestMapper
import com.dyckster.data.mapper.ChartResponseMapper
import com.dyckster.data.source.api.response.ChartResponseModel
import com.dyckster.data.source.api.service.BitcoinService
import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.ChartType
import com.dyckster.domain.model.Timespan
import com.dyckster.utils.TrampolineSchedulerExtension
import com.dyckster.utils.mockkRelaxed
import io.mockk.every
import io.mockk.verify
import io.reactivex.Single
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TrampolineSchedulerExtension::class)
internal class BitcoinChartRepositoryImplTest {

    private val service = mockkRelaxed<BitcoinService>()
    private val queryRequestMapper = mockkRelaxed<ChartQueryRequestMapper>()
    private val chartResponseMapper = mockkRelaxed<ChartResponseMapper>()

    private val repository = BitcoinChartRepositoryImpl(
        chartQueryRequestMapper = queryRequestMapper,
        chartResponseMapper = chartResponseMapper,
        service = service
    )

    @Test
    fun `should get chart`() {
        val inputUrl = "url"
        val inputTimespan = "timespan"
        val serviceResponse = mockkRelaxed<ChartResponseModel>()
        val expectedModel = mockkRelaxed<ChartModel>()
        every {
            queryRequestMapper.mapUrl(ChartType.MARKET_CAP)
        } returns inputUrl
        every {
            queryRequestMapper.maptimeSpan(Timespan.YEAR_5)
        } returns inputTimespan
        every {
            service.getChart(inputUrl, inputTimespan)
        } returns Single.just(serviceResponse)
        every {
            chartResponseMapper.map(serviceResponse)
        } returns expectedModel

        val testObservable = repository.getChart(ChartType.MARKET_CAP, Timespan.YEAR_5).test()

        testObservable.assertValue(expectedModel)
        verify(exactly = 1) {
            queryRequestMapper.mapUrl(ChartType.MARKET_CAP)
            queryRequestMapper.maptimeSpan(Timespan.YEAR_5)
            service.getChart(inputUrl, inputTimespan)
            chartResponseMapper.map(serviceResponse)
        }
    }

    @Test
    fun `should get chart types`() {
        val expected = listOf(
            ChartType.BITCOIN_CIRCULATION,
            ChartType.MARKET_PRICE,
            ChartType.MARKET_CAP,
            ChartType.TRADE_VOLUME,
            ChartType.TRANSACTION_NUM
        )

        val actual = repository.getChartTypes()

        assertThat(actual).isEqualTo(expected)
    }
}