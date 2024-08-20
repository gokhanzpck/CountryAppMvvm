package com.gokhanzopcuk.countryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gokhanzopcuk.countryapp.R
import com.gokhanzopcuk.countryapp.databinding.ItemCountryBinding
import com.gokhanzopcuk.countryapp.model.Country
import com.gokhanzopcuk.countryapp.ui.FeedFragment
import com.gokhanzopcuk.countryapp.ui.FeedFragmentDirections

class CountryAdapter(var countryList:ArrayList<Country>,var con:Context):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){
    class CountryViewHolder(var binding:ItemCountryBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
      val recylerRowBinding=ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryViewHolder(recylerRowBinding)
    }

    override fun getItemCount(): Int {
       return countryList.size
    }
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
          holder.binding.name.text=countryList[position].countryName
          holder.binding.region.text=countryList[position].countryRegion


        Glide.with(con).load(countryList[position].ImageUrl).into(holder.binding.imageView)



        holder.itemView.setOnClickListener {
          val aa=holder.binding.name.text
            val bb=holder.binding.region.text
            val cc =holder.binding.name.text
            val dd =holder.binding.name.text
            val ee =holder.binding.name.text
            val ii=holder.binding.name.text


        }
    }
    fun updateCountryList(newCountryList:List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }


}