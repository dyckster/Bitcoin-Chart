package com.dyckster.base.ui.chart.screen

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.dyckster.base.ui.R
import com.dyckster.base.ui.base.ActivityStarter
import com.dyckster.base.ui.base.BaseActivity
import com.dyckster.base.ui.base.extractInitParams
import com.dyckster.base.ui.chart.animator.FingerEmojiAnimator
import com.dyckster.base.ui.chart.formatter.ChartTimeFormatter
import com.dyckster.base.ui.chart.formatter.NumberPeriodsFormatter
import com.dyckster.base.ui.chart.mapper.TimespanChipViewMapper
import com.dyckster.base.ui.chart.model.ChartUiModel
import com.dyckster.base.ui.chart.model.SelectedValueUiModel
import com.dyckster.base.ui.util.*
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.material.chip.ChipGroup
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

private const val CHART_ANIMATION_DURATION = 100

class BitcoinChartActivity : BaseActivity(R.layout.activity_chart), BitcoinChartView {

    @Inject
    lateinit var provider: Provider<BitcoinChartPresenter>

    @Inject
    lateinit var numberPeriodsFormatter: NumberPeriodsFormatter

    @Inject
    lateinit var chartTimeFormatter: ChartTimeFormatter

    @Inject
    lateinit var timespanChipViewMap: TimespanChipViewMapper

    @Inject
    lateinit var fingerEmojiAnimator: FingerEmojiAnimator

    private val presenter by moxyPresenter {
        val presenter = provider.get()
        presenter.init(extractInitParams())
        presenter
    }

    private val toolbar by bindView<Toolbar>(R.id.chart_toolbar)

    private val dragTipEmoji by bindView<View>(R.id.chart_finger_emoji)
    private val dragTipText by bindView<TextView>(R.id.chart_drag_tip)

    private val bitcoinChart by bindView<LineChart>(R.id.bitcoin_chart)
    private val chartProgress by bindView<View>(R.id.chart_progress)
    private val chartDescription by bindView<TextView>(R.id.chart_description)

    private val selectedValueAmount by bindView<TextView>(R.id.selected_value_amount)
    private val selectedValueDate by bindView<TextView>(R.id.selected_value_date)
    private val chartTimespanGroup by bindView<ChipGroup>(R.id.chart_timespan_group)

    private val chartErrorGroup by bindView<View>(R.id.chart_error_group)
    private val chartErrorDescription by bindView<TextView>(R.id.error_group_description)
    private val chartErrorRetryButton by bindView<View>(R.id.error_group_retry)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.withBackButton(this)

        configureChart()
        chartErrorRetryButton.setOnClickListener { presenter.onRetry() }
        chartTimespanGroup.setOnCheckedChangeListener { _, checkedId ->
            val timeSpan = timespanChipViewMap.map(checkedId)
            presenter.changeTimespan(timeSpan)
        }
        chartTimespanGroup.check(R.id.chart_timespan_month)
    }

    override fun animateDragTip() {
        val animator = fingerEmojiAnimator.prepareAnimator(dragTipEmoji)
        animator.start()
    }

    override fun setScreenTitle(title: String) {
        toolbar.title = title
    }

    override fun setChartData(chartModel: ChartUiModel) {
        chartErrorGroup.makeGone()

        chartDescription.text = chartModel.chartDescription
        bitcoinChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {
                chartModel.onChartValueSelected?.invoke(e.x, e.y)
            }

            override fun onNothingSelected() {
                chartModel.onNoChartValueSelected?.invoke()
            }
        })

        bitcoinChart.data = configureLineData(chartModel)
        bitcoinChart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return chartTimeFormatter.format(value, chartModel.timespan)
            }
        }
        bitcoinChart.animateX(CHART_ANIMATION_DURATION)
        bitcoinChart.invalidate()
    }

    private fun configureChart() {
        bitcoinChart.setScaleEnabled(false)
        bitcoinChart.axisLeft.isEnabled = false
        bitcoinChart.axisRight.typeface = Typeface.DEFAULT_BOLD
        bitcoinChart.axisRight.setDrawAxisLine(false)
        bitcoinChart.axisRight.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return numberPeriodsFormatter.format(value.toLong())
            }
        }

        bitcoinChart.xAxis.position = XAxis.XAxisPosition.TOP_INSIDE
        bitcoinChart.xAxis.setDrawGridLines(false)
        bitcoinChart.xAxis.setDrawAxisLine(false)
        bitcoinChart.xAxis.typeface = Typeface.DEFAULT_BOLD
        bitcoinChart.legend.isEnabled = false
        bitcoinChart.description.isEnabled = false
        val rightOffset = resources.getDimension(R.dimen.chart_right_padding)
        bitcoinChart.setViewPortOffsets(0f, 0f, rightOffset, 0f)
        bitcoinChart.setDrawGridBackground(false)
    }

    private fun configureLineData(model: ChartUiModel): LineData {
        val lineDataSet = LineDataSet(model.chartEntries, null)
        lineDataSet.setDrawFilled(true)
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.highLightColor = ContextCompat.getColor(this, model.chartColor)
        lineDataSet.setDrawHorizontalHighlightIndicator(false)
        lineDataSet.lineWidth = model.chartLineWidth
        lineDataSet.fillColor = ContextCompat.getColor(this, model.chartColor)
        lineDataSet.color = ContextCompat.getColor(this, model.chartColor)
        return LineData(listOf<ILineDataSet>(lineDataSet))
    }

    override fun showProgress() {
        chartErrorGroup.makeGone()
        chartProgress.makeVisible()
    }

    override fun hideProgress() {
        chartProgress.makeGone()
    }

    override fun showSelectedValue(value: SelectedValueUiModel) {
        dragTipEmoji.makeInvisible()
        dragTipText.makeInvisible()

        selectedValueDate.text = value.date
        selectedValueDate.makeVisible()
        selectedValueAmount.text = value.value
        selectedValueAmount.makeVisible()
    }

    override fun hideSelectedValue() {
        dragTipEmoji.makeVisible()
        dragTipText.makeVisible()

        selectedValueAmount.makeInvisible()
        selectedValueDate.makeInvisible()
    }

    override fun showError(errorDescription: Int) {
        chartErrorGroup.makeVisible()
        chartErrorDescription.setText(errorDescription)
    }

    companion object : ActivityStarter() {

        override fun activityClass() = BitcoinChartActivity::class.java
    }
}