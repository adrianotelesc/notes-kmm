import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private var navController = NavController()
    
	var body: some View {
        NavigationStack(path: $navController.path) {
            NotesView()
                .navigationDestination(for: NavController.Destination.self) { value in
                    switch(value) {
                    case .noteEditor(let noteId): 
                        NoteEditorView(noteId: noteId)
                    }
                }
        }
        .environmentObject(navController)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
            .environmentObject(NavController())
	}
}
