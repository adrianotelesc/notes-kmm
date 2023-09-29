package com.adrianotelesc.notes.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

expect abstract class ViewModel<S: Any, E: Any>() {
    val viewModelScope: CoroutineScope

    abstract val uiState: StateFlow<S>

    abstract val sideEffect: SharedFlow<E>

    protected open fun onCleared()
}
