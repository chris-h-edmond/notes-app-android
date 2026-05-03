package com.example.notesappproj.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesappproj.data.model.Note
import com.example.notesappproj.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: NoteViewModel,
    noteId: String?,
    navController: NavController
) {
    val notes by viewModel.notes.collectAsState()
    val note = notes.find { it.id == noteId }

    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }

    // Sync state when note loads from DataStore
    LaunchedEffect(note) {
        if (note != null) {
            title = note.title
            content = note.content
        }
    }

    fun saveAndExit() {
        note?.let {
            viewModel.updateNote(it.copy(title = title, content = content))
        }
        navController.popBackStack()
    }

    Scaffold(
        containerColor = Color(0xFF0D0D1A),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Edit Note",
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFE0E0FF)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { saveAndExit() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF9B6DFF)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0D0D1A)
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Title field
            TextField(
                value = title,
                onValueChange = { title = it },
                placeholder = {
                    Text(
                        "Title",
                        color = Color(0xFF555577),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                textStyle = TextStyle(
                    color = Color(0xFFE0E0FF),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFF9B6DFF),
                    unfocusedIndicatorColor = Color(0xFF2A2A3A),
                    cursorColor = Color(0xFF9B6DFF)
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(8.dp))

            // Content field
            TextField(
                value = content,
                onValueChange = { content = it },
                placeholder = {
                    Text(
                        "Write something...",
                        color = Color(0xFF555577),
                        fontSize = 16.sp
                    )
                },
                textStyle = TextStyle(
                    color = Color(0xFFCCCCDD),
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color(0xFF9B6DFF)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }
    }
}
