package com.adrianotelesc.notes.di

import com.adrianotelesc.notes.data.repository.NoteRepository
import com.adrianotelesc.notes.data.repository.NoteRepositoryImpl
import com.adrianotelesc.notes.ui.screen.notes.NotesViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val repoModule = module {
    singleOf<NoteRepository>(::NoteRepositoryImpl)
}

val viewModelModule = module {
    viewModel { NotesViewModel(noteRepo = get()) }
}

val sharedModules = viewModelModule + repoModule
