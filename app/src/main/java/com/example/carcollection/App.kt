package com.example.carcollection

import android.app.Application
import com.example.carcollection.di.AppComponent
import com.example.carcollection.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(application)
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        lateinit var application: Application
    }
}