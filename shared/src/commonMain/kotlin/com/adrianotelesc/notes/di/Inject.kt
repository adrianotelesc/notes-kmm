package com.adrianotelesc.notes.di

import com.adrianotelesc.notes.core.ViewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

expect inline fun <reified VM : ViewModel<*, *>> Module.viewModel(
    noinline definition: Definition<VM>
): KoinDefinition<VM>
