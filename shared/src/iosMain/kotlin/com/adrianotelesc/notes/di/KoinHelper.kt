package com.adrianotelesc.notes.di

import com.adrianotelesc.notes.ui.screen.notes.NotesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinHelper: KoinComponent {
    val notesViewModel: NotesViewModel by inject()
}

fun startKoin(){
    startKoin {
        modules(modules = sharedModules)
    }
}
