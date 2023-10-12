package com.adrianotelesc.notes.ui.screen.notes

import com.adrianotelesc.notes.core.ViewModel
import com.adrianotelesc.notes.data.model.Note
import com.adrianotelesc.notes.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepo: NoteRepository,
) : ViewModel<NotesUiState, NotesSideEffect>() {
    private val _uiState = MutableStateFlow(value = NotesUiState())
    override val uiState: StateFlow<NotesUiState> = _uiState

    private val _sideEffect = MutableSharedFlow<NotesSideEffect>()
    override val sideEffect: SharedFlow<NotesSideEffect> = _sideEffect

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            noteRepo.notes.collect { notes ->
                _uiState.update { uiState ->
                    uiState.copy(notes = notes)
                }
            }
        }
    }

    fun addNote() {
        viewModelScope.launch {
            val noteCount = _uiState.value.notes.size + 1
            val text = if (noteCount % 2 == 0) {
                "This is note  $noteCount.\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Integer congue metus accumsan aliquet vestibulum. Sed pellentesque diam tincidunt ligula sollicitudin porttitor."
            } else {
                "This is note $noteCount."
            }
            noteRepo.addNote(Note(text = text))
        }
    }
}
