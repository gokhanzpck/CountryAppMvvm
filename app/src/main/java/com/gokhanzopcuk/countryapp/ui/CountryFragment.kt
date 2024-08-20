package com.gokhanzopcuk.countryapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gokhanzopcuk.countryapp.R
import com.gokhanzopcuk.countryapp.databinding.FragmentCountryBinding
import com.gokhanzopcuk.countryapp.viewmodel.FeedViewModel
import com.gokhanzopcuk.countryapp.viewmodel.countryViewModel

class CountryFragment : Fragment() {
    private lateinit var binding: FragmentCountryBinding
    private lateinit var viewModel: countryViewModel

     private var contryuuid=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding=FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            contryuuid=CountryFragmentArgs.fromBundle(it).countryuuid
        }
        viewModel=ViewModelProvider(this).get(countryViewModel::class.java)
        viewModel.getDataFromRoom(contryuuid)
        println("a101")
obserLiveData()
    }
     fun obserLiveData(){
       println("a102")
         viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {country->
         country?.let {
             println("a100")
             binding.countryName.text=country.countryName
             binding.countryCapital.text=country.countryCapital
             binding.countryCurrency.text=country.countryCapital
             binding.countryLanguage.text=country.countryLanguage
             binding.countryRegion.text=country.countryRegion
         }
             if (country==null){
                 println({country})
             }
         })
     }
}