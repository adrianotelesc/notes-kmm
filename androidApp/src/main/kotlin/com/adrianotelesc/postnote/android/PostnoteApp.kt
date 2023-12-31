package com.adrianotelesc.postnote.android

import android.app.Application
import com.adrianotelesc.postnote.di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class PostnoteApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@PostnoteApp)
            modules(modules = sharedModules)
        }
    }
}
