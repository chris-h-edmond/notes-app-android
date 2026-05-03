package com.example.notesappproj.data.repository

import com.example.notesappproj.data.datastore.NoteDataStore
import com.example.notesappproj.data.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class NoteRepository(private val dataStore: NoteDataStore) {

    fun getNotes(): Flow<List<Note>> = dataStore.getNotes()

    suspend fun addNote(note: Note) {
        val current = dataStore.getNotes().first().toMutableList()
        current.add(note)
        dataStore.saveNotes(current)
    }

    suspend fun updateNote(updated: Note) {
        val list = dataStore.getNotes().first().toMutableList()
        val index = list.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            list[index] = updated
            dataStore.saveNotes(list)
        }
    }

    suspend fun deleteNote(id: String) {
        val list = dataStore.getNotes().first().toMutableList()
        list.removeAll { it.id == id }
        dataStore.saveNotes(list)
    }
}
