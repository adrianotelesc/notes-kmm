package com.adrianotelesc.notes.android.ui.screen.notes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adrianotelesc.notes.android.MyApplicationTheme
import com.adrianotelesc.notes.data.model.Note
import com.adrianotelesc.notes.ui.screen.notes.NotesViewModel

@Composable
fun NotesScreen(viewModel: NotesViewModel = NotesViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    NotesContent(notes = uiState.notes)
}

@Composable
private fun NotesContent(notes: List<Note>) {
    Scaffold { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(notes) { note ->
                Text(text = note.text)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesContentPreview() {
    MyApplicationTheme {
        NotesScreen()
    }
}
