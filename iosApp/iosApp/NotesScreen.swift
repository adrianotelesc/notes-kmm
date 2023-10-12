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

struct NotesScreen : View {
    @ObservedObject var viewModel = ObservableNotesViewModel()
    
    var body: some View {
        NavigationView {
            ScrollView {
                VMasonry(columns: 2, spacing: 8) {
                    ForEach(viewModel.uiState.notes, id: \.text) { note in
                        Text(note.text)
                            .lineLimit(10)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .padding(16)
                            .background(Color(uiColor: .secondarySystemBackground))
                            .cornerRadius(8)
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
                    Button(action: {
                        viewModel.addNote()
                    }) {
                        Image(systemName: "square.and.pencil").imageScale(.large)
                    }
                }
                
            }
        }
    }
}
