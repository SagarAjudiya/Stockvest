package com.simform.stockvest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simform.stockvest.databinding.ItemTransactionListBinding
import com.simform.stockvest.model.TransactionDays

class TransactionDaysAdapter(private val transactionDaysList: ArrayList<TransactionDays>) :
    RecyclerView.Adapter<TransactionDaysAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(var binding: ItemTransactionListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transactionDays: TransactionDays) {
            binding.apply {
                this.transactionDays = transactionDays
                rvTransaction.adapter = TransactionAdapter(transactionDays.transactionList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val holder = TransactionViewHolder(
            ItemTransactionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        holder.binding.rvTransaction.layoutManager = LinearLayoutManager(parent.context)
        return holder
    }

    override fun getItemCount(): Int {
        return transactionDaysList.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactionDaysList[position])
    }

}