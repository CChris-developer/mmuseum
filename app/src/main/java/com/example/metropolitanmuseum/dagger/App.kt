package com.example.metropolitanmuseum.dagger

import android.app.Application

class App : Application() {
    companion object {
        lateinit var component: DaggerComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerDaggerComponent.builder().build()
    }
}