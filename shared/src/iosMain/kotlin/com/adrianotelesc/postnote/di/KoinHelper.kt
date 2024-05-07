package com.adrianotelesc.postnote.di

import org.koin.core.context.startKoin

fun startKoin() {
    startKoin {
        modules(modules = sharedModules)
    }
}
