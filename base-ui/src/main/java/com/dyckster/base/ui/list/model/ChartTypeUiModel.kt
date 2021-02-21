package com.dyckster.base.ui.list.model

data class ChartTypeUiModel(
    val chartName: String
) {
    var onClickAction: (() -> Unit)? = null
}