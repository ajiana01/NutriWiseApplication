package ch2ps299.ajiananta.nutriwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutriWiseApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NutriWiseApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

