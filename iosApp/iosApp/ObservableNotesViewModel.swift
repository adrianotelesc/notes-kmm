//
//  ObservableNotesViewModel.swift
//  iosApp
//
//  Created by Adriano Teles on 24/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import Combine

class ObservableNotesViewModel: ObservableObject {
    @Published var uiState: NotesUiState = NotesUiState(notes: [])
    
    private var uiStateCloseable : Closeable?
    
    private var uiEffectCloseable : Closeable?
    let uiEffectPublisher = PassthroughSubject<NotesUiEffect, Never>()
    
    private var viewModel: NotesViewModel
    
    init(viewModel: NotesViewModel = KoinHelper().notesViewModel) {
        self.viewModel = viewModel

        uiStateCloseable = self.viewModel.watchUiState().watch { [weak self] uiState in
            self?.uiState = uiState
        }
        uiEffectCloseable = self.viewModel.watchUiEffect().watch { [weak self] uiEffect in
            self?.uiEffectPublisher.send(uiEffect)
        }
    }
    
    deinit {
        uiStateCloseable?.close()
        uiEffectCloseable?.close()
    }
    
    func newNote() {
        viewModel.doNewNote()
    }
    
    func openNote(noteId: String?) {
        viewModel.openNote(noteId: noteId)
    }
}
