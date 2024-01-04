package com.adrianotelesc.postnote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform