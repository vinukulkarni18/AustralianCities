package com.vinod.australiancities.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import com.vinod.australiancities.cities.DisplayCitiesListScreen
import com.vinod.australiancities.ui.theme.DarkColorScheme
import com.vinod.australiancities.ui.theme.LightColorScheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme (colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme) {
                DisplayCitiesListScreen()
            }
        }
    }
}