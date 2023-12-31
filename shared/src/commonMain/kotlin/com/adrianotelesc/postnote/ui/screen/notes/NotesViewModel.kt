package com.adrianotelesc.postnote.ui.screen.notes

import com.adrianotelesc.postnote.core.ViewModel
import com.adrianotelesc.postnote.data.model.Note
import com.adrianotelesc.postnote.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepo: NoteRepository,
) : ViewModel<NotesUiState, NotesUiEffect>() {
    private val _uiState = MutableStateFlow(value = NotesUiState())
    override val uiState: StateFlow<NotesUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<NotesUiEffect>()
    override val uiEffect: SharedFlow<NotesUiEffect> = _uiEffect

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

    fun newNote() {
        viewModelScope.launch {
            _uiEffect.emit(value = NotesUiEffect.NewNote)
        }
    }

    fun openNote(noteId: String?) {
        viewModelScope.launch {
            _uiEffect.emit(value = NotesUiEffect.OpenNote(noteId = noteId))
        }
    }
}
