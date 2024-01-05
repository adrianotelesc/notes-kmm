package com.adrianotelesc.postnote.ui.screen.notes

sealed class NotesUiEffect {
    data object NewNote : NotesUiEffect()
    data class OpenNote(val noteId: String?) : NotesUiEffect()
}
