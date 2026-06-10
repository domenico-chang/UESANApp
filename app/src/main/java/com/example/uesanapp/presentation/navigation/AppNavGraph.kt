package com.example.uesanapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uesanapp.data.repository.LocalRepository
import com.example.uesanapp.presentation.auth.LoginScreen
import com.example.uesanapp.presentation.auth.RegisterScreen
import com.example.uesanapp.presentation.home.FavoritesScreen
import com.example.uesanapp.presentation.home.HomeScreen

@Composable
fun AppNavGraph(repository: LocalRepository) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "register"
    ) {
        composable("register") {
            RegisterScreen(navController, repository)
        }

        composable("login") {
            LoginScreen(navController, repository)
        }

        composable("home") {
            DrawerScaffold(navController) {
                HomeScreen(repository)
            }
        }

        composable("favorites") {
            DrawerScaffold(navController) {
                FavoritesScreen(repository)
            }
        }
    }
}