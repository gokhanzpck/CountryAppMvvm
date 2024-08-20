package com.gokhanzopcuk.countryapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

class CustomSharedPrefences {

    companion object{
        private val PREFENCES_TIME="preferences_time"
        private  var sharedPreferences:SharedPreferences?=null
        @Volatile private var instance:CustomSharedPrefences?=null
      private val lock=Any()

        operator fun invoke(context: Context):CustomSharedPrefences= instance ?: kotlin.synchronized(lock){
            instance ?: makeCustomSharedPrefences(context).also {
                instance=it

            }
        }
        private fun makeCustomSharedPrefences(context: Context):CustomSharedPrefences{
            sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPrefences()

        }
    }
fun saveTime(time:Long){
    sharedPreferences?.edit(commit = true){
        putLong(PREFENCES_TIME,time)
    }
}
    fun getTime()= sharedPreferences?.getLong(PREFENCES_TIME,0)


}