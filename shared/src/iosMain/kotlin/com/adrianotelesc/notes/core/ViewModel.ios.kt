package com.adrianotelesc.notes.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

actual abstract class ViewModel<S : Any, E : Any> {
    actual val viewModelScope: CoroutineScope = MainScope()

    actual abstract val uiState: StateFlow<S>

    actual abstract val sideEffect: SharedFlow<E>

    inline fun watchUiState(): CFlow<S> = uiState.wrap()

    inline fun watchSideEffect(): CFlow<E> = sideEffect.wrap()

    protected actual open fun onCleared() {
    }
}
