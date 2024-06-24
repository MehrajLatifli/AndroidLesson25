package com.example.androidlesson25.source.api.repositories

import android.util.Log
import com.example.androidlesson25.models.responses.get.coin.CoinResponse
import com.example.androidlesson25.source.api.IApiManager
import com.example.androidlesson25.source.api.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CoinRepository @Inject constructor(private val api: IApiManager) {

    suspend fun getAllCoins(): Resource<CoinResponse> {
        return safeApiRequest {
            api.getAllCoins(/*API_KEY*,*/)
        }
    }



    suspend fun <T> safeApiRequest(request: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {

                val response = request.invoke()

                if (response.isSuccessful) {
                    Resource.Success(response.body())
                } else {
                    Resource.Error(response.message())
                }
            } catch (e: Exception) {
                Log.e("APIFailed",e.message.toString())
                Resource.Error(e.localizedMessage)
            }
        }
    }
}