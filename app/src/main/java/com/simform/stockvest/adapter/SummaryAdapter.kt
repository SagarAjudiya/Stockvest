package com.simform.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simform.stockvest.databinding.ItemTextStockDetailsBinding
import com.simform.stockvest.model.StockSummary

class SummaryAdapter(private val summaryList: ArrayList<StockSummary>) :
    RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>() {

    inner class SummaryViewHolder(var binding: ItemTextStockDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(summary: StockSummary) {
            binding.summary = summary
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        return SummaryViewHolder(
            ItemTextStockDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return summaryList.size
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        holder.bind(summaryList[position])
    }

}