package com.example.rotationsubn

import android.app.Application
import timber.log.Timber

class RnApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
