package com.adrianotelesc.postnote

import platform.Foundation.NSUUID

expect fun generateUuid(): String = NSUUID().UUIDString()