package com.example.features.cars.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.App
import com.example.features.addcar.presentation.AddCarFragment
import com.example.features.cars.di.CarsComponent
import com.example.features.cars.di.DaggerCarsComponent
import com.example.features.databinding.FragmentCarsBinding
import com.example.features.infocar.presentation.InfoCarFragment
import com.example.features.settings.presentation.SetFragment

class CarsFragment : Fragment() {


    private lateinit var binding: FragmentCarsBinding
    private lateinit var adapter: CarsAdapter

    private val component: CarsComponent by lazy {
        DaggerCarsComponent.factory()
            .create(((activity?.applicationContext as? App)?.appComponent!!))
    }
    private val viewModel: CarsViewModel by viewModels {
        component.factoryCarsViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarMainScreen.setSettingsClickListener {
            val setFragment = SetFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(com.example.core.R.id.fragment_container, setFragment)
                .addToBackStack(null).commit()
        }

        binding.floatingActionButton.setOnClickListener {
            val addCarFragment = AddCarFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(com.example.core.R.id.fragment_container, addCarFragment)
                .addToBackStack(null).commit()
        }
        search()
        rcView()

    }

    private fun rcView() = with(binding) {
        carsRV.layoutManager = LinearLayoutManager(activity)
        adapter = CarsAdapter { item ->
            val infoCarFragment = InfoCarFragment()
            val bundle = Bundle().apply {
                putString("name", item.name)
                putString("image", item.image)
                putInt("year", item.year)
                putDouble("engine", item.engine)
                putLong("date", item.date)
            }
            infoCarFragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(com.example.core.R.id.fragment_container, infoCarFragment)
                .addToBackStack(null)
                .commit()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    is CarsViewState.Content -> {

                        adapter.submitList(state.cars)
                    }
                    is CarsViewState.Empty -> {
                    }
                    else -> {
                    }
                }
            }
        }

        carsRV.adapter = adapter


    }

    private fun search() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchCars(newText)
                return true
            }
        })
        return
    }


    companion object {
        fun newInstance() = CarsFragment()
    }
}