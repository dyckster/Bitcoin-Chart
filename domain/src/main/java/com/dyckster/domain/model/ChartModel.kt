package com.dyckster.domain.model

data class ChartModel(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<ChartValueModel>
)