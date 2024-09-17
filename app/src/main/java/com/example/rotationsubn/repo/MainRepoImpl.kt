package com.example.rotationsubn.repo

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    @ApplicationContext context: Context
) : MainRepo {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE)

    override var dimension: Int
        get() = sharedPreferences.getInt(DIMENSION, 3)
        set(value) {
            sharedPreferences.edit().putInt(DIMENSION, value).apply()
        }

    override val minDimension: Int
        get() = 3

    override val maxDimension: Int
        get() = 4

    private companion object {
        const val SHARED_PREFS_NAME = "main shared prefs"
        const val DIMENSION = "dimension"
    }
}
