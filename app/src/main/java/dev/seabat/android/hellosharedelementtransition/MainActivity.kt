package dev.seabat.android.hellosharedelementtransition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.seabat.android.hellosharedelementtransition.ui.compose.CoffeeListDetail
import dev.seabat.android.hellosharedelementtransition.ui.theme.HelloSharedElementTransitionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloSharedElementTransitionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoffeeListDetail(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}