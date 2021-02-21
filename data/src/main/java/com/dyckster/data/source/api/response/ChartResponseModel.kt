package com.dyckster.data.source.api.response

import com.google.gson.annotations.SerializedName

data class ChartResponseModel(
    @SerializedName("status")
    val status: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("period")
    val period: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("values")
    val values: List<ChartValueResponseModel>
)