package com.example.features.cars.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.App
import com.example.features.addcar.di.AddCarComponent
import com.example.features.addcar.di.DaggerAddCarComponent
import com.example.features.addcar.presentation.AddCarFragment
import com.example.features.databinding.FragmentCarsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarsFragment : Fragment() {

    private lateinit var binding: FragmentCarsBinding
    private lateinit var adapter: CarsAdapter

    private val component: AddCarComponent by lazy {
        DaggerAddCarComponent.factory()
            .create(((activity?.applicationContext as? App)?.appComponent!!))
    } // test

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
        GlobalScope.launch {
            val carsList = component.providesRoom().getAllCars()
            withContext(Dispatchers.Main) {
                adapter.submitList(carsList) // test
            }
        }

        carsRV.adapter = adapter

    }

    companion object {
        fun newInstance() = CarsFragment()
    }
}