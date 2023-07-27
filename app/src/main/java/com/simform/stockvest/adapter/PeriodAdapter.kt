package com.simform.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simform.stockvest.databinding.ItemPeriodicRowBinding
import com.simform.stockvest.model.Period

class PeriodAdapter(private val periods: ArrayList<Period>) :
    RecyclerView.Adapter<PeriodAdapter.PeriodViewHolder>() {

    inner class PeriodViewHolder(var binding: ItemPeriodicRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(period: Period) {
            binding.period = period
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
        return PeriodViewHolder(
            ItemPeriodicRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return periods.size
    }

    override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {
        holder.bind(periods[position])
    }

}