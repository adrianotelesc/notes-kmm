//
//  StickyNote.swift
//  iosApp
//
//  Created by Adriano Teles on 14/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct StickyNoteView: View {
    var text: String = ""
    
    init(text: String) {
        self.text = text
    }
    
    var body: some View {
        Text(text)
            .lineLimit(10)
            .truncationMode(.tail)
            .frame(maxWidth: .infinity, alignment: .topLeading)
            .padding(16)
            .background(Color(uiColor: .secondarySystemBackground))
            .cornerRadius(8)
    }
}

struct StickyNoteViewPreview: PreviewProvider {
    static var previews: some View {
        StickyNoteView(text: "Note here")
    }
}
