package com.dyckster.base.ui.chart.mapper

import com.dyckster.base.ui.R
import com.dyckster.base.ui.chart.model.ChartUiModel
import com.dyckster.domain.model.ChartModel
import com.dyckster.domain.model.Timespan
import javax.inject.Inject

private const val CHART_LINE_WIDTH = 2.5f

class ChartUiMapper
@Inject constructor() {

    fun map(chartModel: ChartModel, timespan: Timespan): ChartUiModel {
        return ChartUiModel(
            chartDescription = chartModel.description,
            timespan = timespan,
            chartColor = R.color.colorPrimary,
            chartLineWidth = CHART_LINE_WIDTH,
            chartEntries = chartModel.values.map { chartValueModel ->
                ChartEntry(chartValueModel.date, chartValueModel.value)
            }
        )
    }
}