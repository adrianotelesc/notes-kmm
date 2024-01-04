package com.adrianotelesc.notes.android.ui.screen.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adrianotelesc.notes.android.R
import com.adrianotelesc.notes.android.ui.theme.MyApplicationTheme
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

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)
@Composable
private fun NotesContent(
    notes: List<Note>,
    onAddClick: () -> Unit,
) {
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = appBarState)

    val listState = rememberLazyStaggeredGridState()

    val showButton by remember {
        derivedStateOf { appBarState.collapsedFraction == 0f }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Notes") },
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = showButton,
                enter = scaleIn(
                    animationSpec = keyframes {
                        durationMillis = 120
                    },
                ),
                exit = scaleOut(
                    animationSpec = keyframes {
                        durationMillis = 120
                    },
                ),
            ) {
                FloatingActionButton(onClick = { onAddClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                    )
                }
            }
        }
    ) { padding ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            columns = StaggeredGridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            verticalItemSpacing = 8.dp,
            contentPadding = PaddingValues(all = 16.dp),
            state = listState,
        ) {
            items(notes) { note ->
                Card {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = note.text,
                    )
                }
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
