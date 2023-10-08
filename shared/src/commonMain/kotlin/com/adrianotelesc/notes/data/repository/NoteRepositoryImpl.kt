package com.adrianotelesc.notes.data.repository

import com.adrianotelesc.notes.data.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class NoteRepositoryImpl : NoteRepository {
    private val _notes = MutableStateFlow<List<Note>>(value = emptyList())
    override val notes: Flow<List<Note>> = _notes

    override fun addNote(note: Note) {
        _notes.update { notes ->
            notes.toMutableList().apply { add(0, note) }
        }
    }
}
