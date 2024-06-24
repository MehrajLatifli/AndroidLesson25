package com.example.androidlesson25.views.fragments.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidlesson25.R
import com.example.androidlesson25.databinding.FragmentHomeBinding
import com.example.androidlesson25.utilities.gone
import com.example.androidlesson25.utilities.visible
import com.example.androidlesson25.viewmodels.HomeViewModel
import com.example.androidlesson25.views.adapters.CoinAdapder
import com.example.androidlesson25.views.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val coinAdapder = CoinAdapder()


    var coindata=""
    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        viewModel.getAllCoins()


        val spanCount = if (isTablet()) 2 else 1
        val gridLayoutManager = GridLayoutManager(context, spanCount)
        binding.recycleViewCoin.layoutManager = gridLayoutManager
        binding.recycleViewCoin.adapter = coinAdapder

    }






    private fun observeData() {
        viewModel.coins.observe(viewLifecycleOwner) { cities ->
            coinAdapder.updateList(cities)
        }



        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBarContainer.progressBar2.visible()
            } else {
                binding.progressBarContainer.progressBar2.gone()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun isTablet(): Boolean {
        val configuration = resources.configuration
        return configuration.smallestScreenWidthDp >= 600
    }


}