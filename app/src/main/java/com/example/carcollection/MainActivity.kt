package com.example.carcollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.features.cars.presentation.CarsFragment
import com.example.core.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setInitialScreen()
        }
    }

    private fun setInitialScreen() {
        val fragment = CarsFragment.newInstance()
        supportFragmentManager.commit {
            replace(
                com.example.core.R.id.fragment_container,
                fragment,
                fragment::javaClass.name
            )
            addToBackStack(null)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}