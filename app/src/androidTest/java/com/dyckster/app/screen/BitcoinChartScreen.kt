package com.dyckster.app.screen

import com.agoda.kakao.chipgroup.KChipGroup
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.text.KTextView
import com.dyckster.base.ui.R
import com.dyckster.base.ui.chart.screen.BitcoinChartActivity
import com.kaspersky.kaspresso.screens.KScreen

class BitcoinChartScreen : KScreen<BitcoinChartScreen>() {

    override val layoutId = R.layout.activity_chart
    override val viewClass = BitcoinChartActivity::class.java

    val chartDragTip = KView { withId(R.id.chart_finger_emoji) }
    val chartView = KView { withId(R.id.bitcoin_chart) }
    val selectedValueAmount = KView { withId(R.id.selected_value_amount) }
    val timespansChipGroup = KChipGroup { withId(R.id.chart_timespan_group) }
    val chartDescription = KTextView { withId(R.id.chart_description) }
}