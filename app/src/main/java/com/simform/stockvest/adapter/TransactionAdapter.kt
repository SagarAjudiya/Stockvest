package com.simform.stockvest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.simform.stockvest.databinding.ItemTransactionStockBinding
import com.simform.stockvest.databinding.LayoutBottomOrderDetailsBinding
import com.simform.stockvest.enums.TransactionStatus
import com.simform.stockvest.model.Transaction


class TransactionAdapter(private val transactionList: ArrayList<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(var binding: ItemTransactionStockBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            binding.transaction = transaction
            setUpStatus(transaction.status)
        }

        private fun setUpStatus(status: Int) {
            val statusType = TransactionStatus.valueOf(status) ?: TransactionStatus.OPEN
            binding.textView3.text = statusType.toString()
            binding.textView3.setTextColor(
                ResourcesCompat.getColor(
                    binding.root.resources, statusType.getColor(), binding.root.context.theme
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val holder = TransactionViewHolder(
            ItemTransactionStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        holder.binding.root.setOnClickListener {
            showBottomSheetDialog(parent.context, transactionList[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactionList[position])
    }

    private fun showBottomSheetDialog(context: Context, transaction: Transaction) {
        val binding = LayoutBottomOrderDetailsBinding.inflate(LayoutInflater.from(context))
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(binding.root)
        binding.apply {
            this.transaction = transaction
            val statusType = TransactionStatus.valueOf(transaction.status) ?: TransactionStatus.OPEN
            tvStatus.text = statusType.toString()
            tvStatus.setTextColor(
                ResourcesCompat.getColor(
                    root.resources, statusType.getColor(), root.context.theme
                )
            )
            cancelButton.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
        }
        bottomSheetDialog.show()
    }

}