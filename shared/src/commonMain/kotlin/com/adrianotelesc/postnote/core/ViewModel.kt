package com.adrianotelesc.postnote.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

expect abstract class ViewModel<S: Any, E: Any>() {
    val viewModelScope: CoroutineScope

    abstract val uiState: StateFlow<S>

    abstract val uiEffect: SharedFlow<E>

    protected open fun onCleared()
}
