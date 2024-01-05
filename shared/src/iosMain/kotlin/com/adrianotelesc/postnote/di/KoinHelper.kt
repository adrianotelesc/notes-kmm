package com.adrianotelesc.postnote.di

import com.adrianotelesc.postnote.ui.screen.noteeditor.NoteEditorViewModel
import com.adrianotelesc.postnote.ui.screen.notes.NotesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinHelper : KoinComponent {
    val notesViewModel: NotesViewModel by inject()
    val noteEditorViewModel: NoteEditorViewModel by inject()
}

fun startKoin() {
    startKoin {
        modules(modules = sharedModules)
    }
}
