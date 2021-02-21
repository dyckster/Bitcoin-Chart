package com.dyckster.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.dyckster.data.source.api.response.ChartValueResponseModel
import com.dyckster.domain.model.ChartValueModel
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class ChartValueResponseMapperTest {

    private val mapper = ChartValueResponseMapper()

    @Test
    fun `should map`() {
        val inputValue = ChartValueResponseModel(
            x = 1L,
            y = BigDecimal("1.767250129E11")
        )
        val expected = ChartValueModel(
            date = 1000L,
            value = BigDecimal("1.767250129E11")
        )

        val actual = mapper.map(inputValue)

        assertThat(actual).isEqualTo(expected)
    }
}