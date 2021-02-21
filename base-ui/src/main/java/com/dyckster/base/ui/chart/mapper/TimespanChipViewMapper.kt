package com.dyckster.base.ui.chart.mapper

import androidx.annotation.IdRes
import com.dyckster.base.ui.R
import com.dyckster.domain.model.Timespan
import javax.inject.Inject

class TimespanChipViewMapper
@Inject constructor() {

    fun map(@IdRes resId: Int): Timespan {
        return when (resId) {
            R.id.chart_timespan_week -> Timespan.WEEK
            R.id.chart_timespan_month -> Timespan.MONTH
            R.id.chart_timespan_year -> Timespan.YEAR
            R.id.chart_timespan_3_years -> Timespan.YEAR_3
            R.id.chart_timespan_5_years -> Timespan.YEAR_5
            else -> throw IllegalStateException("Unknown resid for timespan")
        }
    }
}