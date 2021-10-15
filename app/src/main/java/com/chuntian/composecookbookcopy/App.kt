package com.chuntian.composecookbookcopy

import android.app.Application
import android.content.Context
import timber.log.Timber

class App : Application() {
    init {
        instance = requireNotNull(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        private lateinit var instance: App
        fun applicationContext(): Context = instance
    }
}