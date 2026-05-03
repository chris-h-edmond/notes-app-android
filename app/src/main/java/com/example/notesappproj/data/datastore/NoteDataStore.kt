package com.example.notesappproj.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.notesappproj.data.model.Note
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "notes")

class NoteDataStore(private val context: Context) {

    private val NOTES_KEY = stringPreferencesKey("notes_list")

    fun getNotes(): Flow<List<Note>> =
        context.dataStore.data.map { prefs ->
            val json = prefs[NOTES_KEY] ?: "[]"
            Gson().fromJson(json, Array<Note>::class.java).toList()
        }

    suspend fun saveNotes(notes: List<Note>) {
        context.dataStore.edit { prefs ->
            prefs[NOTES_KEY] = Gson().toJson(notes)
        }
    }
}
