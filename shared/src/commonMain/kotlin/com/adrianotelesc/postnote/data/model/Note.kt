package com.adrianotelesc.postnote.data.model

import com.adrianotelesc.postnote.generateUuid

data class Note(
    val id: String = generateUuid(),
    val text: String = "",
) {
    val isEmpty: Boolean get() = text.isEmpty() || text.isBlank()

    val isNotEmpty: Boolean get() = !isEmpty
}
