//
//  NotesScreen.swift
//  iosApp
//
//  Created by Adriano Teles on 24/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NotesScreen : View {
    @ObservedObject var viewModel = ObservableNotesViewModel()
    
    var body: some View {
        NavigationView {
            List(viewModel.uiState.notes, id: \.self) {
                Text($0.text)
            }
            .toolbar {
                Button(action: {
                    viewModel.addNote()
                }) {
                    Image(systemName: "plus").imageScale(.large)
                }
            }
        }
    }
}
