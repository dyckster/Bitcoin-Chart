package com.dyckster.base.ui.chart.mapper

import com.dyckster.base.ui.chart.formatter.NumberPeriodsFormatter
import com.dyckster.base.ui.chart.model.SelectedValueUiModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SelectedChartValueMapper
@Inject constructor(
    private val numberPeriodsFormatter: NumberPeriodsFormatter
) {

    private val dateFormat by lazy {
        SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
    }

    fun map(value: Long, date: Float, unit: String): SelectedValueUiModel {
        val readableValue = numberPeriodsFormatter.format(value)
        return SelectedValueUiModel(
            value = "$readableValue $unit",
            date = dateFormat.format(date.toLong())
        )
    }
}