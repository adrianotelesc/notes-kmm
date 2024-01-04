package com.adrianotelesc.postnote.di

import com.adrianotelesc.postnote.core.ViewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.androidx.viewmodel.dsl.viewModel as androidXKoinViewModel

actual inline fun <reified VM : ViewModel<*, *>> Module.viewModel(
    noinline definition: Definition<VM>
): KoinDefinition<VM> = androidXKoinViewModel(qualifier = null, definition = definition)
