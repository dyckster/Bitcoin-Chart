package com.dyckster.base.ui.chart.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.tableOf
import com.dyckster.base.ui.R
import com.dyckster.domain.model.Timespan
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TimespanChipViewMapperTest {

    private val mapper = TimespanChipViewMapper()

    @Test
    fun `should map timespan chip id`() {
        tableOf("inputResId", "timespan")
            .row(R.id.chart_timespan_week, Timespan.WEEK)
            .row(R.id.chart_timespan_month, Timespan.MONTH)
            .row(R.id.chart_timespan_year, Timespan.YEAR)
            .row(R.id.chart_timespan_3_years, Timespan.YEAR_3)
            .row(R.id.chart_timespan_5_years, Timespan.YEAR_5)
            .forAll { resId, expectedTimespan ->
                val actual = mapper.map(resId)

                assertThat(actual).isEqualTo(expectedTimespan)
            }
    }

    @Test
    fun `should not map unknown id and throw expection`() {
        assertThrows<IllegalStateException> {
            mapper.map(-1)
        }
    }
}