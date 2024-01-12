//
//  NoteEditorView.swift
//  iosApp
//
//  Created by Adriano Teles on 12/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct NoteEditorView: View {
    @StateObject private var viewModel = ObservableNoteEditorViewModel()
    
    @FocusState private var focused: Bool
    
    @State private var text = ""
    
    init(noteId: String? = nil) {
        viewModel.loadNoteBy(id: noteId)
    }
    
    var body: some View {
        TextEditor(text: $text)
            .navigationBarTitleDisplayMode(.inline)
            .focused($focused)
            .onAppear(perform: {
                self.focused = text.isEmpty
            })
            .autocorrectionDisabled()
            .textInputAutocapitalization(.none)
    }
}

struct NoteEditorViewPreview: PreviewProvider {
    static var previews: some View {
        NoteEditorView()
    }
}

