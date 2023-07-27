package com.simform.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.simform.stockvest.activities.StockDetailsActivity
import com.simform.stockvest.databinding.ItemStockHomeBinding
import com.simform.stockvest.model.Stock

class StockAdapter : ListAdapter<Stock, StockAdapter.StockViewHolder>(DiffCallBack()), Filterable {

    var stockList: ArrayList<Stock> = ArrayList()
    var stockListFiltered: ArrayList<Stock> = ArrayList()

    inner class StockViewHolder(var binding: ItemStockHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stock) {
            binding.stock = stock
        }
    }

    private class DiffCallBack : DiffUtil.ItemCallback<Stock>() {
        override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val holder = StockViewHolder(
            ItemStockHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        holder.binding.root.setOnClickListener {
            parent.context.startActivity(
                StockDetailsActivity.getIntent(
                    parent.context, stockPosition = stockListFiltered[holder.adapterPosition].id
                )
            )
        }
        return holder
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterString = constraint.toString().lowercase()
            val filteredList = stockList.filter { it.name.lowercase().contains(filterString) }

            return FilterResults().apply {
                values = filteredList
                count = filteredList.count()
            }
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            (results?.values as? ArrayList<Stock>)?.let { filteredList ->
                submitFilteredList(filteredList)
            }

        }
    }

    private fun submitFilteredList(list: MutableList<Stock>?) {
        list?.let {
            stockListFiltered.clear()
            stockListFiltered.addAll(it)
            super.submitList(ArrayList(it))
        }
    }

    override fun submitList(list: MutableList<Stock>?) {
        list?.let {
            stockList.clear()
            stockList.addAll(it)
            stockListFiltered.clear()
            stockListFiltered.addAll(it)
            super.submitList(ArrayList(it))
        }
    }

    override fun getItemCount(): Int {
        return stockListFiltered.size
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stockListFiltered[position])
    }

}