package com.dyckster.data.mapper

import com.dyckster.data.source.api.response.ChartResponseModel
import com.dyckster.domain.model.ChartModel
import javax.inject.Inject

class ChartResponseMapper
@Inject constructor(
    private val chartValueResponseMapper: ChartValueResponseMapper
) {

    fun map(chartResponseModel: ChartResponseModel): ChartModel {
        val values = chartResponseModel.values.map { valueModel ->
            chartValueResponseMapper.map(valueModel)
        }

        return ChartModel(
            name = chartResponseModel.name,
            description = chartResponseModel.description,
            period = chartResponseModel.period,
            unit = chartResponseModel.unit,
            values = values
        )
    }
}