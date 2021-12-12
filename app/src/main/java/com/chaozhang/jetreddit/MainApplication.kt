package com.chaozhang.jetreddit

import android.app.Application
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {

    @Inject lateinit var configuration: Configuration

    override fun getWorkManagerConfiguration(): Configuration {
        return configuration
    }
}
