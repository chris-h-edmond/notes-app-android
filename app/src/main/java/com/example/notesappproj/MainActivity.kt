package com.example.notesappproj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.notesappproj.data.datastore.NoteDataStore
import com.example.notesappproj.data.repository.NoteRepository
import com.example.notesappproj.ui.navigation.NavGraph
import com.example.notesappproj.ui.theme.NotesAppProjTheme
import com.example.notesappproj.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dataStore = NoteDataStore(applicationContext)
        val repo = NoteRepository(dataStore)
        val viewModel = NoteViewModel(repo)

        setContent {
            NotesAppProjTheme {
                NavGraph(viewModel)
            }
        }
    }
}