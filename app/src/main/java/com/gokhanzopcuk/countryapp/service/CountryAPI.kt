package com.gokhanzopcuk.countryapp.service

import com.gokhanzopcuk.countryapp.model.Country

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountryAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries():Single<List<Country>>


}