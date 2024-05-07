//
//  ObservableNotesViewModel.swift
//  iosApp
//
//  Created by Adriano Teles on 24/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import Combine

extension NotesView {
    @MainActor class ViewModel: ObservableViewModel<NotesUiState, NotesUiEffect> {
        private var viewModel: NotesViewModel
        
        init(viewModel: NotesViewModel = GetViewModels().notesViewModel) {
            self.viewModel = viewModel
            super.init(initialUiState: NotesUiState.Companion().default(), viewModel: viewModel)
        }

        func loadNotes() {
            viewModel.loadNotes()
        }
        
        func createOrOpenNote(note: Note? = nil) {
            viewModel.createOrOpenNote(note: note)
        }
    }
}
