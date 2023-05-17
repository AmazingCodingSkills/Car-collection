package com.example.carcollection.cars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carcollection.R
import com.example.carcollection.databinding.CustomToolbarBinding
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

    companion object {
        fun newInstance() = CarsFragment()
    }
}