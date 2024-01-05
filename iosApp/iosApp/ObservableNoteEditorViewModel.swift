//
//  ObservableNoteEditorViewModel.swift
//  iosApp
//
//  Created by Adriano Teles on 05/01/24.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import Combine

class ObservableNoteEditorViewModel: ObservableObject {
    @Published var uiState: NoteEditorUiState = NoteEditorUiState(note: Note(id: "", text: ""))
    
    private var uiStateCloseable : Closeable?
    
    private var uiEffectCloseable : Closeable?
    let uiEffectPublisher = PassthroughSubject<NoteEditorUiEffect, Never>()
    
    private var viewModel: NoteEditorViewModel
    
    init(viewModel: NoteEditorViewModel = KoinHelper().noteEditorViewModel) {
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
    
    func loadNoteBy(id: String?) {
        viewModel.loadNoteBy(id: id)
    }
    
    func updateNote(text: String) {
        viewModel.updateNote(text: text)
    }
}
