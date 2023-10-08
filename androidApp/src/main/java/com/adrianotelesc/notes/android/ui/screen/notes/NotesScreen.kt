package com.adrianotelesc.notes.android.ui.screen.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.adrianotelesc.notes.android.MyApplicationTheme
import com.adrianotelesc.notes.android.R
import com.adrianotelesc.notes.data.model.Note
import com.adrianotelesc.notes.ui.screen.notes.NotesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesScreen(viewModel: NotesViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    NotesContent(
        notes = uiState.notes,
        onAddClick = viewModel::addNote
    )
}

@Composable
private fun NotesContent(
    notes: List<Note>,
    onAddClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
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
