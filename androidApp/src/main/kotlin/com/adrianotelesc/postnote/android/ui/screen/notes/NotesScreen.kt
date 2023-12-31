package com.adrianotelesc.postnote.android.ui.screen.notes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.adrianotelesc.postnote.android.R
import com.adrianotelesc.postnote.android.ui.component.StickyNote
import com.adrianotelesc.postnote.android.ui.preview.NotesPreviewParameterProvider
import com.adrianotelesc.postnote.android.ui.theme.MyApplicationTheme
import com.adrianotelesc.postnote.android.util.collectInLaunchedEffectWithLifecycle
import com.adrianotelesc.postnote.data.model.Note
import com.adrianotelesc.postnote.ui.screen.notes.NotesUiEffect
import com.adrianotelesc.postnote.ui.screen.notes.NotesUiState
import com.adrianotelesc.postnote.ui.screen.notes.NotesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel(),
    newNote: () -> Unit = {},
    openNote: (noteId: String?) -> Unit = {},
) {
    viewModel.uiEffect.collectInLaunchedEffectWithLifecycle { uiEffect ->
        when (uiEffect) {
            NotesUiEffect.NewNote -> newNote()
            is NotesUiEffect.OpenNote -> openNote(uiEffect.noteId)
        }
    }

    val uiState by viewModel.uiState.collectAsState()
    Content(
        uiState = uiState,
        newNote = viewModel::newNote,
        openNote = viewModel::openNote,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Content(
    uiState: NotesUiState = NotesUiState(),
    newNote: () -> Unit = {},
    openNote: (noteId: String?) -> Unit = {},
) {
    val listState = rememberLazyStaggeredGridState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Notes") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { newNote() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                )
            }
        },
    ) { padding ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = padding),
            columns = StaggeredGridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            verticalItemSpacing = 8.dp,
            contentPadding = PaddingValues(all = 16.dp),
            state = listState,
        ) {
            items(items = uiState.notes) { note ->
                StickyNote(
                    id = note.id,
                    text = note.text,
                    onClick = openNote,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview(
    @PreviewParameter(NotesPreviewParameterProvider::class) notes: List<Note>,
) {
    MyApplicationTheme {
        Content(uiState = NotesUiState(notes = notes))
    }
}
