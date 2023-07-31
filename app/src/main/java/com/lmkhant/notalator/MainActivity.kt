package com.lmkhant.notalator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.lmkhant.notalator.navigation.BottomBarScreens
import com.lmkhant.notalator.navigation.BottomNavHost
import com.lmkhant.notalator.navigation.BottomAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navScreens = listOf(BottomBarScreens.Home, BottomBarScreens.Add, BottomBarScreens.Library)
            AppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    bottomBar = {
                        BottomAppBar(navController = navController, items = navScreens)
                    }
                ) { innerPadding ->
                    BottomNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {

    }
}