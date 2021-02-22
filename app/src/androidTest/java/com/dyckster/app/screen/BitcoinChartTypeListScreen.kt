package com.dyckster.app.screen

import com.agoda.kakao.recycler.KRecyclerView
import com.dyckster.base.ui.R
import com.dyckster.base.ui.list.screen.BitcoinChartListActivity
import com.kaspersky.kaspresso.screens.KScreen

class BitcoinChartTypeListScreen : KScreen<BitcoinChartTypeListScreen>() {

    override val layoutId = R.layout.activity_chart_list
    override val viewClass = BitcoinChartListActivity::class.java

    val typeList = KRecyclerView(
        builder = { withId(R.id.chart_type_recycler) },
        itemTypeBuilder = {}
    )
}