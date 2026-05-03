package com.example.notesappproj.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesappproj.ui.screens.HomeScreen
import com.example.notesappproj.ui.screens.NoteScreen
import com.example.notesappproj.ui.screens.SplashScreen
import com.example.notesappproj.viewmodel.NoteViewModel

@Composable
fun NavGraph(viewModel: NoteViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }

        composable("home") {
            HomeScreen(viewModel, navController)
        }

        composable("note/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")
            NoteScreen(viewModel, id, navController)
        }
    }
}
