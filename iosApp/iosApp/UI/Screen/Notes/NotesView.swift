//
//  NotesScreen.swift
//  iosApp
//
//  Created by Adriano Teles on 24/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import SwiftUIMasonry

struct NotesView : View {
    @EnvironmentObject private var navController: NavController
    
    @StateObject var viewModel = ViewModel()
    
    var body: some View {
        ScrollView {
            VMasonry(columns: 2, spacing: 8) {
                ForEach(viewModel.uiState.notes, id: \.text) { note in
                    StickyNoteView(text: note.text)
                        .onTapGesture(perform: { viewModel.createOrOpenNote(note: note) })
                }
            }
            .padding(16)
        }
        .navigationTitle("Notes")
        .toolbar {
            ToolbarItemGroup(placement: .bottomBar) {
                Spacer()
                let noteCount = viewModel.uiState.notes.count
                let text = if noteCount > 0 {
                    String(AttributedString(localized: "^[\(noteCount) Note](inflect: true)").characters)
                } else {
                    "No Notes"
                }
                Text(text)
                    .padding(EdgeInsets.init(top: 0, leading: 40, bottom: 0, trailing: 0))
                Spacer()
                Button(action: { viewModel.createOrOpenNote() }) {
                    Image(systemName: "square.and.pencil").imageScale(.large)
                }
            }
        }
        .onAppear(perform: { viewModel.loadNotes() })
        .onReceive(viewModel.uiEffectPublisher) { uiEffect in
            switch (uiEffect) {
            case let navigateToNoteEditorUiEffect as NotesUiEffect.NavigateToNoteEditor:
                navController.navigate(to: .noteEditor(noteId: navigateToNoteEditorUiEffect.noteId))
            default:
                break
            }
        }
    }
}

struct NotesViewPreview: PreviewProvider {
    static var previews: some View {
        NotesView()
    }
}
