package com.simform.stockvest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.simform.stockvest.R
import com.simform.stockvest.adapter.StockAdapter
import com.simform.stockvest.databinding.FragmentHomeBinding
import com.simform.stockvest.model.Stock

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var stockList = Stock.data
    private lateinit var stockAdapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.stock = stockList.first()
        binding.rvStockHome.apply {
            layoutManager = LinearLayoutManager(context)
            stockAdapter = StockAdapter()
            adapter = stockAdapter
        }
        binding.chipGroupTags.setOnCheckedStateChangeListener { group, checkedIds ->
            when (group.checkedChipId) {
                binding.chipTrending.id -> stockAdapter.submitList(stockList.filter { it.category.isTrending }
                    .toMutableList())

                binding.chipTopGainer.id -> stockAdapter.submitList(stockList.filter { it.category.isTopGainer }
                    .toMutableList())

                binding.chipTopLoser.id -> stockAdapter.submitList(stockList.filter { it.category.isTopLoser }
                    .toMutableList())

                binding.chipMostActive.id -> stockAdapter.submitList(stockList.filter { it.category.isMostActive }
                    .toMutableList())

                else -> stockAdapter.submitList(stockList.filter { it.category.isTrending }
                    .toMutableList())
            }
            binding.edSearch.text?.clear()
        }
        binding.edSearch.doOnTextChanged { text, start, before, count ->
            stockAdapter.filter.filter(text)
        }
        stockAdapter.submitList(stockList.filter { it.category.isTrending }.toMutableList())
    }

}