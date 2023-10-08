//
//  ObservableNotesViewModel.swift
//  iosApp
//
//  Created by Adriano Teles on 24/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared

class ObservableNotesViewModel: ObservableObject {
    @Published var uiState: NotesUiState = NotesUiState(notes: [])
    
    private var uiStateWatcher : Closeable?
    
    private var viewModel: NotesViewModel
    
    init(viewModel: NotesViewModel = KoinHelper().notesViewModel) {
        self.viewModel = viewModel
        uiStateWatcher = self.viewModel.watchUiState().watch { [weak self] state in
            self?.uiState = state
        }
    }
    
    deinit {
        uiStateWatcher?.close()
    }
    
    func addNote() {
        viewModel.addNote()
    }
}
