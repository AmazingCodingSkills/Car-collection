package com.example.features.cars.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.App
import com.example.features.addcar.di.AddCarComponent
import com.example.features.addcar.di.DaggerAddCarComponent
import com.example.features.addcar.presentation.AddCarFragment
import com.example.features.addcar.presentation.AddCarViewModel
import com.example.features.cars.di.CarsComponent
import com.example.features.cars.di.DaggerCarsComponent
import com.example.features.databinding.FragmentCarsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        binding.floatingActionButton.setOnClickListener {
            val addCarFragment = AddCarFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(com.example.core.R.id.fragment_container, addCarFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        rcView()
    }

    private fun rcView() = with(binding) {
        carsRV.layoutManager = LinearLayoutManager(activity)
        adapter = CarsAdapter { item -> }
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

    companion object {
        fun newInstance() = CarsFragment()
    }
}