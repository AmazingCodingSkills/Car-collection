package com.example.features.settings.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.App
import com.example.core.databinding.FragmentSetBinding
import com.example.features.settings.di.DaggerSetComponent
import com.example.features.settings.di.SetComponent


class SetFragment : Fragment() {

    private lateinit var binding: FragmentSetBinding


    private val component: SetComponent by lazy {
        DaggerSetComponent.factory()
            .create(((activity?.applicationContext as? App)?.appComponent!!))
    }

    private val viewModel: SetViewModel by viewModels {
        component.factorySetViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarMainScreen.setBackClickListener {
            val fr = requireActivity().supportFragmentManager
            if (fr.backStackEntryCount > 0) {
                fr.popBackStack()
            }
        }
        binding.reload.setOnClickListener{
            viewModel.handleAction(SetViewAction.ReloadCounts)
        }
        observeViewEvents()
    }

    private fun observeViewEvents() {

        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { state ->
                when (state) {
                    is SetViewEvents.DismissScreen -> {
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                }
            }
        }
    }
}