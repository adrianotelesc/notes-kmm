package com.adrianotelesc.postnote.android.util

import androidx.compose.ui.text.input.TextFieldValue

val TextFieldValue.selectionLine: Int get() =
    text.take(selection.start).count { it == '\n' } + 1
