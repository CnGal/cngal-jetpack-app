package com.cngal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.cngal.app.compose.overview.MainScreen
import com.cngal.app.compose.overview.OverviewScreen
import com.cngal.app.ui.theme.CnGalTheme
import com.funny.data_saver.core.DataSaverPreferences
import com.funny.data_saver.core.LocalDataSaver

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
 

        setContent {
            CnGalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Box()
                    {
                        val dataSaverPreferences =
                            DataSaverPreferences(context = applicationContext, false)
                        CompositionLocalProvider(LocalDataSaver provides dataSaverPreferences) {
                            MainScreen()
                        }
                    }

                }
            }
        }
    }
}
