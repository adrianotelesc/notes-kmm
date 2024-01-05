package com.adrianotelesc.postnote

import java.util.UUID

actual fun generateUuid(): String = UUID.randomUUID().toString()