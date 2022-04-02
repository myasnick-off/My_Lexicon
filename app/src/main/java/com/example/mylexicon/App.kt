package com.example.mylexicon

import android.app.Application
import com.example.mylexicon.di.component.DaggerAppComponent

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        lateinit var appInstance: App
    }
}
