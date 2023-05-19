package com.example.features.addcar.presentation

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.App
import com.example.core.subscriptiondialog.SubscriptionDialogFragment
import com.example.core.view.hideKeyboard
import com.example.core.view.showKeyboard
import com.example.features.addcar.di.AddCarComponent
import com.example.features.addcar.di.DaggerAddCarComponent
import com.example.features.databinding.FragmentAddCarBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class AddCarFragment : Fragment() {

    private lateinit var binding: FragmentAddCarBinding
    private lateinit var contentResolver: ContentResolver
    private var subscriptionDialogFragment: SubscriptionDialogFragment? = null


    private val component: AddCarComponent by lazy {
        DaggerAddCarComponent.factory()
            .create(((activity?.applicationContext as? App)?.appComponent!!))
    }
    private val viewModel: AddCarViewModel by viewModels {
        component.factoryAddCarViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contentResolver = requireContext().contentResolver

        binding.toolbarMainScreen.setBackClickListener {
            close()
        }
        binding.editNameCar.requestFocus()
        binding.editNameCar.showKeyboard()

        addImageCar()
        setupViews()
        observeViewEvents()

    }

    private fun close() {
        val fr = requireActivity().supportFragmentManager
        if (fr.backStackEntryCount > 0) {
            fr.popBackStack()
        }
    }

    private fun setupViews() {

        binding.editNameCar?.addTextChangedListener {
            val input = it.toString()
            viewModel.handleAction(AddCarViewAction.NameCar(input))
        }

        binding.editYearCar.addTextChangedListener {
            val input = it.toString()
            viewModel.handleAction(AddCarViewAction.YearCar(input.toInt()))
        }

        binding.editEngineCar.addTextChangedListener {
            val input = it.toString()
            viewModel.handleAction(AddCarViewAction.EngineCar(input.toDouble()))
        }


        binding.addButton.setOnClickListener {
            viewModel.handleAction(AddCarViewAction.SaveCar)
            binding.addButton.hideKeyboard()
        }
    }

    private fun observeViewEvents() {

        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { state ->
                when (state) {
                    is AddCarViewEvents.DismissScreen -> {
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                    is AddCarViewEvents.ShowDialog -> {
                        showSubscriptionDialog()
                    }
                    is AddCarViewEvents.ValidationErrorDialog -> {
                        view?.let {
                            Snackbar.make(
                                it,
                                com.example.core.R.string.message,
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun addImageCar() {
        binding.imageCar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_PHOTO_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_PHOTO_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                viewModel.handleAction(AddCarViewAction.ImageCar(saveBitmapToInternalStorage(bitmap)))
                if (bitmap != null) {
                    binding.imageCar.setImageBitmap(bitmap)
                } else {
                    Log.e("AddCarFragment", "Selected image bitmap is null")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }


    private fun saveBitmapToInternalStorage(bitmap: Bitmap): String {
        val context = App.application.applicationContext
        val directory = context.getDir(
            "images",
            Context.MODE_PRIVATE
        )

        val file = File(
            directory,
            "image_${System.currentTimeMillis()}.jpg"
        )

        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                100,
                outputStream
            )
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file.absolutePath
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


    companion object {
        const val PICK_PHOTO_REQUEST = 1
    }
}