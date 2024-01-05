package com.adrianotelesc.postnote

import platform.Foundation.NSUUID

actual fun generateUuid(): String = NSUUID().UUIDString()