package com.adrianotelesc.notes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform