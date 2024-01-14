package com.adrianotelesc.postnote.di

import com.adrianotelesc.postnote.core.ViewModel
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

expect inline fun <reified VM : ViewModel<*, *>, reified T1> Module.viewModelOf(
    noinline constructor: (T1) -> VM,
): KoinDefinition<VM>
