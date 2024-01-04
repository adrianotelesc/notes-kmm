package com.adrianotelesc.postnote.data.repository

import com.adrianotelesc.postnote.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    val notes: Flow<List<Note>>

    fun addNote(note: Note)
}
