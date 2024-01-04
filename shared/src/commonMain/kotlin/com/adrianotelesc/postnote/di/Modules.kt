package com.adrianotelesc.postnote.di

import com.adrianotelesc.postnote.data.repository.NoteRepository
import com.adrianotelesc.postnote.data.repository.NoteRepositoryImpl
import com.adrianotelesc.postnote.ui.screen.notes.NotesViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val repoModule = module {
    singleOf<NoteRepository>(::NoteRepositoryImpl)
}

val viewModelModule = module {
    viewModel { NotesViewModel(noteRepo = get()) }
}

val sharedModules = viewModelModule + repoModule
