package com.example.features.infocar.presentation

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.App
import com.example.core.subscriptiondialog.SubscriptionDialogFragment
import com.example.features.databinding.FragmentInfoCarBinding
import com.example.features.infocar.di.DaggerInfoCarComponent
import com.example.features.infocar.di.InfoCarComponent
import java.text.SimpleDateFormat
import java.util.*


class InfoCarFragment : Fragment() {

    private lateinit var binding: FragmentInfoCarBinding

    private var argOne: String? = null
    private var argTwo: String? = null
    private var argThree: Int? = null
    private var argFour: Double? = null
    private var argFive: Long? = null

    private var subscriptionDialogFragment: SubscriptionDialogFragment? = null

    private val component: InfoCarComponent by lazy {
        DaggerInfoCarComponent.factory()
            .create(((activity?.applicationContext as? App)?.appComponent!!))
    }

    private val viewModel: InfoCarViewModel by viewModels {
        component.factoryInfoCarViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArg()
        viewModel.handleAction(InfoCarViewAction.CountViews)
        observeViewEvents()
        binding.toolbarMainScreen.setBackClickListener {
            close()
        }
    }

    private fun close() {
        val fr = requireActivity().supportFragmentManager
        if (fr.backStackEntryCount > 0) {
            fr.popBackStack()
        }
    }

    private fun getArg() {
        argOne = arguments?.getString("name")
        argTwo = arguments?.getString("image")
        argThree = arguments?.getInt("year")
        argFour = arguments?.getDouble("engine")
        argFive = arguments?.getLong("date")
        val bitmap = BitmapFactory.decodeFile(argTwo)
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(argFive)
        binding.name.text =
            binding.root.context.getString(com.example.core.R.string.car_brand, argOne)
        binding.image.setImageBitmap(bitmap)
        binding.year.text =
            binding.root.context.getString(com.example.core.R.string.car_year, argThree.toString())
        binding.engine.text = binding.root.context.getString(
            com.example.core.R.string.engine_capacity,
            argFour.toString()
        )
        binding.date.text =
            binding.root.context.getString(com.example.core.R.string.date_add, formattedDate)

    }

    private fun observeViewEvents() {

        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { state ->
                when (state) {
                    is InfoCarViewEvents.DismissScreen -> {
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                    is InfoCarViewEvents.ShowDialog -> {
                        showSubscriptionDialog()
                    }
                }
            }
        }
    }

    private fun showSubscriptionDialog() {
        if (subscriptionDialogFragment == null) {
            subscriptionDialogFragment = SubscriptionDialogFragment().apply {
                okAction = {
                    viewModel.buySub()
                }
                cancelAction = {
                    close()
                }
            }
        }
        subscriptionDialogFragment?.show(childFragmentManager, "Dialog")
    }
}