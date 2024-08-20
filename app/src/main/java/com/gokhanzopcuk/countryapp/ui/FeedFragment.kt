package com.gokhanzopcuk.countryapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.gokhanzopcuk.countryapp.R
import com.gokhanzopcuk.countryapp.adapter.CountryAdapter
import com.gokhanzopcuk.countryapp.databinding.FragmentFeedBinding
import com.gokhanzopcuk.countryapp.databinding.FragmentFeedBinding.bind
import com.gokhanzopcuk.countryapp.databinding.FragmentFeedBinding.inflate
import com.gokhanzopcuk.countryapp.model.Country
import com.gokhanzopcuk.countryapp.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private lateinit var viewModel:FeedViewModel
    //private val countryAdapter=CountryAdapter(arrayListOf())
    lateinit var countryAdapter : CountryAdapter
    private lateinit var binding: FragmentFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel=ViewModelProvider(this).get(FeedViewModel::class.java)
        //inişilazed ediyoruz.

        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
       // return inflater.inflate(R.layout.fragment_feed,container,false)
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            countryAdapter= CountryAdapter(arrayListOf(),requireContext())
        viewModel.refreshData()
        binding.countryList.layoutManager=LinearLayoutManager(context)
        binding.countryList.adapter=countryAdapter
 println("a0")
        binding.swapeRefreshData.setOnRefreshListener {
            binding.countryList.visibility=View.VISIBLE
            binding.countryError.visibility=View.GONE
            binding.countryLoading.visibility=View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swapeRefreshData.isRefreshing=false

        }
        obserLiveData()


    /*
    binding.fragmentButton.setOnClickListener {
        Navigation.findNavController(it).navigate(R.id.feedCountryGecis)
    } */
    }
    fun obserLiveData(){
        viewModel.contries.observe(viewLifecycleOwner, Observer {contries ->
          contries?.let {
              binding.countryList.visibility=View.VISIBLE
              countryAdapter.updateCountryList(contries)
          }
        })
        println("a1")

        viewModel.countryError.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                if (it){
                    binding.countryError.visibility=View.VISIBLE

                }else{
                    binding.countryError.visibility=View.INVISIBLE
                }

            }
println("a23")
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {loading->
            loading?.let {
                if (it){
                    binding.countryLoading.visibility=View.VISIBLE
                    binding.countryList.visibility=View.GONE
                    binding.countryError.visibility=View.GONE
                }else{
                    binding.countryLoading.visibility=View.GONE
                }
            }
        })
        /*
        if (viewModel.contries==null){
            println("a4")
        }else{
        viewModel.contries.observe(viewLifecycleOwner) { contries ->
            println("a3")
            // Null kontrolü ekleyin
            binding?.countryList?.visibility = if (contries != null) {
                View.VISIBLE
            } else {
                View.GONE
            }
            contries?.let {
                countryAdapter.updateCountryList(it)
            }
        }

        println("a1")

        viewModel.countryError.observe(viewLifecycleOwner) { error ->
            binding?.countryError?.visibility = if (error == true) View.VISIBLE else View.INVISIBLE
        }

        viewModel.countryLoading.observe(viewLifecycleOwner) { loading ->
            binding?.apply {
                countryLoading.visibility = if (loading == true) View.VISIBLE else View.GONE
                countryList.visibility = if (loading == true) View.GONE else View.VISIBLE
                countryError.visibility = if (loading == true) View.GONE else countryError.visibility
           
            }
        }
    }*/
}
}