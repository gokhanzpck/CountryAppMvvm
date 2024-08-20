package com.gokhanzopcuk.countryapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gokhanzopcuk.countryapp.model.Country
import com.gokhanzopcuk.countryapp.service.CountryAPIService
import com.gokhanzopcuk.countryapp.service.CountryDatabase
import com.gokhanzopcuk.countryapp.util.CustomSharedPrefences

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch


class FeedViewModel(application: Application):BaseViewModel(application) {
    private val countryAPIService=CountryAPIService()
    private val disposable= CompositeDisposable()
    private var customPreferences = CustomSharedPrefences(getApplication())
    private var refreshTime=10 * 60 * 1000*1000*1000L

    val contries =MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()

    fun refreshData(){

        val updateTime=customPreferences.getTime()

        if(updateTime!=null && updateTime !=0L && System.nanoTime()-updateTime<refreshTime ){
            getDataFromSQLite()
        }else{
            getDataFromAPI()
        }

    }
    fun refreshFromAPI() {
        getDataFromAPI()
    }
    private fun getDataFromSQLite(){

        countryLoading.value=true
        launch {
             val countries=CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"Countries From Aplication",Toast.LENGTH_LONG).show()
        }

    }
    private fun getDataFromAPI(){
        countryLoading.value=true
      disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                  showCountries(countryList = t)
                   Toast.makeText(getApplication(),"Countries From API",Toast.LENGTH_LONG).show()

                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value=true
                        countryError.value=true
                        e.printStackTrace()

                    }


                })

        )

    }
    private fun showCountries(countryList: List<Country>){
        contries.value=countryList
        countryError.value=false
        countryLoading.value=false
    }
    private fun storeInSQLite(list: List<Country>){

        launch {
            val dao=CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
           val listlong= dao.insertAll(*list.toTypedArray())
            var i=0
            while (i<list.size){
             list[i].uuid=listlong[i].toInt()
             i=+1
            }
            showCountries(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }
}



