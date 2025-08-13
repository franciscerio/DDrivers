package com.fcerio.ddrivers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fcerio.common.compose.DDriversTheme
import com.fcerio.ddrivers.features.main.composable.MainScreenComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DDriversTheme {
                MainScreenComposable()
            }
        }
    }
}
