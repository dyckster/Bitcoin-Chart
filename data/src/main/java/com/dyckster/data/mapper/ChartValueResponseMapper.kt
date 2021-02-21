package com.dyckster.data.mapper

import com.dyckster.data.source.api.response.ChartValueResponseModel
import com.dyckster.domain.model.ChartValueModel
import javax.inject.Inject

class ChartValueResponseMapper
@Inject constructor() {

    fun map(model: ChartValueResponseModel): ChartValueModel {
        return ChartValueModel(
            date = (model.x * 1000),
            value = model.y
        )
    }
}