package com.dyckster.base.ui.chart.formatter

import com.dyckster.domain.model.Timespan
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ChartTimeFormatter
@Inject constructor() {

    fun format(value: Float, timespan: Timespan): String {
        val timestamp = (value).toLong()
        val datePattern = when (timespan) {
            Timespan.WEEK -> "dd.MM"
            Timespan.MONTH -> "dd.MM"
            Timespan.YEAR -> "MMM"
            Timespan.YEAR_3 -> "YYYY"
            Timespan.YEAR_5 -> "YYYY"
        }
        val format = SimpleDateFormat(datePattern, Locale.getDefault())
        return format.format(timestamp)
    }
}