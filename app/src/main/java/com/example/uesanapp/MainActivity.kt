package com.example.uesanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.uesanapp.data.local.AppDatabase
import com.example.uesanapp.data.repository.LocalRepository
import com.example.uesanapp.presentation.navigation.AppNavGraph
import com.example.uesanapp.ui.theme.UESANAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val repository = LocalRepository(
            countryDao = database.countryDao(),
            userDao = database.userDao()
        )

        setContent {
            UESANAppTheme {
                AppNavGraph(repository)
            }
        }
    }
}