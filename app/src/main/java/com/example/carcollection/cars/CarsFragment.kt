package com.example.carcollection.cars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carcollection.R
import com.example.carcollection.addcar.presentation.AddCarFragment
import com.example.carcollection.databinding.FragmentCarsBinding

class CarsFragment : Fragment() {

    private lateinit var binding: FragmentCarsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val db = Room.databaseBuilder(App.application.applicationContext, AppDatabase::class.java, "cars").build()
        val carsDao = db.carItemDao()
        val carsRepository = CarsRepository(carsDao)
        val car = Cars(2, "Car 1", R.drawable.ic_baseline_add_24.toString(), 2023, 2.0,17)
        GlobalScope.launch {
            //carsRepository.insert(car)
            val x = carsRepository.getAllCars()
            Log.d("FFFF","$x")
        }*/
        binding.floatingActionButton.setOnClickListener{
            val addCarFragment = AddCarFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, addCarFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    companion object {
        fun newInstance() = CarsFragment()
    }
}