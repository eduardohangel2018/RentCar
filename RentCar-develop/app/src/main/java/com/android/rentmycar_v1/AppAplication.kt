package com.android.rentmycar_v1

import android.app.Application
import com.android.rentmycar_v1.service.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppAplication)
            modules(
                listOf(firebaseModule)
            )
        }
    }
}