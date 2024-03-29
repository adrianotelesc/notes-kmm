package com.adrianotelesc.postnote.ui.screen.noteeditor

import com.adrianotelesc.postnote.core.ViewModel
import com.adrianotelesc.postnote.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteEditorViewModel(
    private val noteRepo: NoteRepository,
) : ViewModel<NoteEditorUiState, NoteEditorUiEffect>() {
    private val _uiState = MutableStateFlow(value = NoteEditorUiState())
    override val uiState: StateFlow<NoteEditorUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<NoteEditorUiEffect>()
    override val uiEffect: SharedFlow<NoteEditorUiEffect> = _uiEffect

    fun loadNoteBy(id: String?) {
        val existingNote = id?.let { noteRepo.findBy(id = id) }
        _uiState.update { uiState ->
            uiState.copy(
                note = existingNote ?: uiState.note,
                isActive = true,
            )
        }
    }

    fun updateNote(text: String) {
        val updatedNote = _uiState.value.note.copy(text = text)
        _uiState.update { uiState -> uiState.copy(note = updatedNote) }
        noteRepo.update(note = updatedNote)
    }

    fun navigateUp() {
        viewModelScope.launch {
            _uiEffect.emit(value = NoteEditorUiEffect.NavigateUp)
        }
    }
}
