package com.example.androidlesson25.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlesson25.models.responses.get.coin.CoinData
import com.example.androidlesson25.source.api.Resource
import com.example.androidlesson25.source.api.repositories.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: CoinRepository
) : ViewModel() {

    private val _coins = MutableLiveData<List<CoinData>>()
    val coins: LiveData<List<CoinData>> = _coins



    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    private var originalCoinList = listOf<CoinData>()

    init {
        _loading.value = false
        _error.value = null

    }

    fun getAllCoins() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.getAllCoins()
            withContext(Dispatchers.Main) {
                when (response) {
                    is Resource.Success -> {
                        _loading.value = false
                        val itemResponse = response.data
                        if (itemResponse != null) {
                            _coins.value = itemResponse.data
                            originalCoinList = itemResponse.data
                        } else {
                            _error.value = "No cities found"
                            _coins.value = emptyList()
                            Log.e("APIFailed",_error.value.toString())

                        }
                    }
                    is Resource.Error -> {
                        _loading.value = false
                        _error.value = "Failed to fetch coins: ${response.message}"
                        Log.e("APIFailed",_error.value.toString())
                    }
                }
            }
        }
    }




    fun search(query: String) {

        if (query.isBlank()) {
            _coins.value = originalCoinList

        }

        val filtered = originalCoinList.filter { item ->
            item.shortName?.contains(query, ignoreCase = true) ?: false
        }
        _coins.value = filtered

    }

}