package com.adrianotelesc.postnote.di

import com.adrianotelesc.postnote.core.ViewModel
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf

actual inline fun <reified VM : ViewModel<*, *>, reified T1> Module.viewModelOf(
    noinline constructor: (T1) -> VM,
): KoinDefinition<VM> = factoryOf(constructor)
