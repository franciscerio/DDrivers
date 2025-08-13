package com.fcerio.ddrivers

import android.app.Application
import com.fcerio.domain.Delivery
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.MutableStateFlow

@HiltAndroidApp
class App : Application() {

    // Temporary implementation just to demo the data
    companion object {
        val DELIVERY = MutableStateFlow<List<Delivery>>(emptyList())
    }

    override fun onCreate() {
        super.onCreate()
    }
}
