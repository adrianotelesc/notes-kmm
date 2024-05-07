//
//  NoteEditorView.swift
//  iosApp
//
//  Created by Adriano Teles on 12/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteEditorView: View {
    @EnvironmentObject private var navController: NavController
    
    @StateObject private var viewModel = ViewModel()
    
    @State private var text: String = ""
    
    @FocusState private var focused: Bool
    
    private var noteId: String?
    
    init(noteId: String? = nil) {
        self.noteId = noteId
    }
    
    var body: some View {
        TextEditor(text: $text)
            .navigationBarTitleDisplayMode(.inline)
            .focused($focused)
            .autocorrectionDisabled()
            .textInputAutocapitalization(.none)
            .onAppear(perform: { viewModel.loadNoteBy(id: noteId) })
            .onChange(of: text) { _, newText in
                viewModel.updateNote(text: text)
            }
            .onChange(of: viewModel.uiState, initial: false) { _, newUiState  in
                text = newUiState.note.text
            }
            .onReceive(viewModel.uiEffectPublisher) { uiEffect in
                switch (uiEffect) {
                case _ as NoteEditorUiEffect.NavigateUp:
                    navController.navigateUp()
                default:
                    break
                }
            }
    }
}

struct NoteEditorViewPreview: PreviewProvider {
    static var previews: some View {
        NoteEditorView()
    }
}
