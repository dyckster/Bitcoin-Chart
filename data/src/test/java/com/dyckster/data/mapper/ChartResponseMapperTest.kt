package com.dyckster.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.dyckster.data.source.api.response.ChartResponseModel
import com.dyckster.data.source.api.response.ChartValueResponseModel
import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.ChartValueModel
import com.dyckster.utils.mockkRelaxed
import io.mockk.every
import org.junit.jupiter.api.Test

internal class ChartResponseMapperTest {

    private val valueMapper = mockkRelaxed<ChartValueResponseMapper>()
    private val mapper = ChartResponseMapper(
        chartValueResponseMapper = valueMapper
    )

    @Test
    fun `should map`() {
        val valueResponseModel = mockkRelaxed<ChartValueResponseModel>()
        val values = listOf(valueResponseModel)
        val valueEntityModel = mockkRelaxed<ChartValueModel>()
        every { valueMapper.map(valueResponseModel) } returns valueEntityModel
        val input = ChartResponseModel(
            status = "ok",
            name = "name",
            unit = "unit",
            period = "period",
            description = "description",
            values = values
        )
        val expected = ChartModel(
            name = "name",
            unit = "unit",
            period = "period",
            description = "description",
            values = listOf(valueEntityModel)
        )

        val actual = mapper.map(input)

        assertThat(actual).isEqualTo(expected)
    }
}