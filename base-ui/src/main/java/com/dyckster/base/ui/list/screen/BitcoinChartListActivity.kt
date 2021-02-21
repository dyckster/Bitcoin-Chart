package com.dyckster.base.ui.list.screen

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.dyckster.base.ui.R
import com.dyckster.base.ui.base.BaseActivity
import com.dyckster.base.ui.chart.screen.BitcoinChartActivity
import com.dyckster.base.ui.chart.screen.BitcoinChartInitParams
import com.dyckster.base.ui.list.adapter.ChartTypeAdapter
import com.dyckster.base.ui.list.model.ChartTypeUiModel
import com.dyckster.base.ui.util.bindView
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class BitcoinChartListActivity : BaseActivity(R.layout.activity_chart_list), BitcoinChartListView {

    @Inject
    lateinit var provider: Provider<BitcoinChartListPresenter>

    private val presenter by moxyPresenter { provider.get() }

    private val chartTypeAdapter = ChartTypeAdapter()
    private val chartTypeRecycler by bindView<RecyclerView>(R.id.chart_type_recycler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chartTypeRecycler.adapter = chartTypeAdapter
    }

    override fun showTypes(types: List<ChartTypeUiModel>) {
        chartTypeAdapter.setItems(types)
    }

    override fun openChart(initParams: BitcoinChartInitParams) {
        BitcoinChartActivity.start(this, initParams)
    }
}