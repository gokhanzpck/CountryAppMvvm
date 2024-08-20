package com.gokhanzopcuk.countryapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gokhanzopcuk.countryapp.model.Country
import com.gokhanzopcuk.countryapp.service.CountryDatabase
import kotlinx.coroutines.launch

class countryViewModel(application: Application):BaseViewModel (application){

    val countryLiveData=MutableLiveData<Country>()
    fun getDataFromRoom(uuid:Int){
        launch {
            println("a104")
            val dao=CountryDatabase(getApplication()).countryDao()
            val country=dao.getCountry(uuid)
            countryLiveData.value=country
        }

    }



}