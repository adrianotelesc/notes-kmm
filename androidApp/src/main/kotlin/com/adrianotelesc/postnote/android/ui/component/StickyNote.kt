package com.adrianotelesc.postnote.android.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.adrianotelesc.postnote.data.model.Note
import com.adrianotelesc.postnote.android.ui.preview.NotePreviewParameterProvider
import com.adrianotelesc.postnote.android.ui.theme.PostnoteTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun StickyNote(
    id: String = "",
    text: String,
    onClick: (id: String?) -> Unit = {},
) {
    Card(onClick = { onClick(id) }) {
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = text,
            maxLines = 10,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
fun PostItPreview(
    @PreviewParameter(NotePreviewParameterProvider::class) note: Note,
) {
    PostnoteTheme {
        StickyNote(text = note.text)
    }
}
