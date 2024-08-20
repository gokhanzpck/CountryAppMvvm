package com.gokhanzopcuk.countryapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gokhanzopcuk.countryapp.model.Country
@Dao
interface CountryDao {
    @Insert
    suspend fun insertAll(vararg countries:Country):List<Long>
    //vararg->sayısı belli olmadıgı zaman bir tekil objeyi tekil olarak vermemizi saglar
    //15 taneyi tek tek veriyor bize
    //long->primery keyimizi döndürüyor

    @Query("SELECT * FROM country")
    suspend fun getAllCountries():List<Country>

    @Query("SELECT * FROM country WHERE uuid= :countryId")
    suspend fun getCountry(countryId:Int):Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()


}