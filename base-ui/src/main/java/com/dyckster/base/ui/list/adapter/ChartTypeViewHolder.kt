package com.dyckster.base.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dyckster.base.ui.R
import com.dyckster.base.ui.list.model.ChartTypeUiModel
import com.dyckster.base.ui.util.bindView

class ChartTypeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_chart_type, parent, false)
) {

    private val chartTypeTextView by bindView<TextView>(R.id.chart_type_name)

    fun bind(model: ChartTypeUiModel) {
        chartTypeTextView.text = model.chartName
        itemView.setOnClickListener {
            model.onClickAction?.invoke()
        }
    }
}