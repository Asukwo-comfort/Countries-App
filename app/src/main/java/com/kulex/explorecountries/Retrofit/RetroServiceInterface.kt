package com.kulex.explorecountries.Retrofit

import com.kulex.explorecountries.Model.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("v3.1/all")
    fun getCountryList(): Call<List<CountryModel>>
}