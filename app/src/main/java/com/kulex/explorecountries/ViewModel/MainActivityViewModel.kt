package com.kulex.explorecountries.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kulex.explorecountries.Model.CountryModel
import com.kulex.explorecountries.Retrofit.RetroInstance
import com.kulex.explorecountries.Retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<CountryModel>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getliveDataObserver(): MutableLiveData<List<CountryModel>> {
        return liveDataList
    }

    fun makeApiCall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getCountryList()
        call.enqueue(object : Callback<List<CountryModel>> {
            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                liveDataList.postValue(response.body())
            }
        })
    }
}