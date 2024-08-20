package com.gokhanzopcuk.countryapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gokhanzopcuk.countryapp.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase :RoomDatabase(){
    abstract fun countryDao():CountryDao
    companion object{
//companion objecti her yerden ulaşmak icin kullanıyoruz
    //singleton---icerisinden tek bir obje oluşturulabilen bir sinıftır

       @Volatile private var instance:CountryDatabase?=null
       //Volatile=>farklı threadlere de görünür hale getiriyor

        private val lock=Any()
       operator fun invoke(context: Context)= instance ?: synchronized(lock){
         //icerde bir instance varmı yokmu kontrol eden fonks yoksa oluştur synchronized da iki threadden bilgi gelirse sıra sıra database e ulaşacak
           instance ?: makeDatabase(context).also {
               instance=it
           }
       }
        private fun makeDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()

    }
}