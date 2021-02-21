package com.dyckster.base.ui.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dyckster.base.ui.list.model.ChartTypeUiModel

class ChartTypeAdapter : RecyclerView.Adapter<ChartTypeViewHolder>() {

    private var items: List<ChartTypeUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartTypeViewHolder {
        return ChartTypeViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ChartTypeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(types: List<ChartTypeUiModel>) {
        this.items = types
        notifyDataSetChanged()
    }
}