package com.example.notesappproj.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappproj.data.model.Note
import com.example.notesappproj.data.repository.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class NoteViewModel(private val repo: NoteRepository) : ViewModel() {

    val notes = repo.getNotes().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addNote() {
        viewModelScope.launch {
            val note = Note(
                id = UUID.randomUUID().toString(),
                title = "",
                content = "",
                timestamp = System.currentTimeMillis()
            )
            repo.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repo.updateNote(note)
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch {
            repo.deleteNote(id)
        }
    }
}
