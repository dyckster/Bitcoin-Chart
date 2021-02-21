package com.dyckster.domain.model

import java.math.BigDecimal

data class ChartValueModel(
    val date: Long,
    val value: BigDecimal
)