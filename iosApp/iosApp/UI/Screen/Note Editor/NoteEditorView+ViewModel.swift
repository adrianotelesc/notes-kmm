//
//  ObservableNoteEditorViewModel.swift
//  iosApp
//
//  Created by Adriano Teles on 05/01/24.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import Combine

extension NoteEditorView {
    @MainActor class ViewModel: ObservableViewModel<NoteEditorUiState, NoteEditorUiEffect> {
        private var viewModel: NoteEditorViewModel
        
        init(viewModel: NoteEditorViewModel = GetViewModels().noteEditorViewModel) {
            self.viewModel = viewModel
            super.init(initialUiState: NoteEditorUiState.Companion().default(), viewModel: viewModel)
        }

        func loadNoteBy(id: String?) {
            viewModel.loadNoteBy(id: id)
        }
        
        func updateNote(text: String) {
            viewModel.updateNote(text: text)
        }
    }
}
