package com.example.rotation_n

import android.app.Application
import timber.log.Timber

class RNApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
