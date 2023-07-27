package com.simform.stockvest.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.simform.stockvest.R
import com.simform.stockvest.adapter.TransactionDaysAdapter
import com.simform.stockvest.databinding.FragmentTransactionBinding
import com.simform.stockvest.model.TransactionDays

class TransactionFragment : Fragment() {

    private lateinit var binding: FragmentTransactionBinding
    private var transactionDaysList = TransactionDays.data

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false)

        binding.rvTransaction.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TransactionDaysAdapter(transactionDaysList)
        }
        return binding.root
    }

}