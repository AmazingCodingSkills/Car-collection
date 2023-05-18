package com.example.carcollection.addcar.presentation

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
import com.example.carcollection.App
import com.example.carcollection.base.room.Cars
import com.example.carcollection.base.room.data.CarItemDao
import com.example.carcollection.base.view.showKeyboard
import com.example.carcollection.databinding.FragmentAddCarBinding
import com.example.carcollection.di.AppComponent
import com.example.carcollection.di.DaggerAppComponent
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class AddCarFragment : Fragment() {

    private lateinit var binding: FragmentAddCarBinding
    private lateinit var contentResolver: ContentResolver
    val myscope = CoroutineScope(Dispatchers.Main)

    private val component: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(App.application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


        binding.toolbarMainScreen.setSettingsClickListener {
            val fr = requireActivity().supportFragmentManager
            if (fr.backStackEntryCount > 0) {
                fr.popBackStack()
            }
        }
        binding.editNameCar.requestFocus()
        binding.editNameCar.showKeyboard()
        addCar()
    }

    private fun addCar() {
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
                val x = component.providesRoom()
                saveCar(bitmap,x)
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

    private fun saveCar(bitmap: Bitmap, carRepository: CarItemDao) {
        val imagePath =
            saveBitmapToInternalStorage(bitmap)

        val cars =
            Cars(name = "nissan", image = imagePath, year = 1985, engine = 3.5, date = 12)
        GlobalScope.launch {
            myscope.launch {
                binding.imageCar.setImageBitmap(bitmap)
                myscope.cancel()
            }
            carRepository.insertCar(cars)
            val x = carRepository.getAllCars()
            Log.d("FFFF", "$x")
        }
    }

    companion object {
        const val PICK_PHOTO_REQUEST = 1
    }
}