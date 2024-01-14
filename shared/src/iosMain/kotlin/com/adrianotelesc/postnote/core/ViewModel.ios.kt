package com.adrianotelesc.postnote.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

actual abstract class ViewModel<S : Any, E : Any> {
    actual val viewModelScope: CoroutineScope = MainScope()

    actual abstract val uiState: StateFlow<S>

    actual abstract val uiEffect: SharedFlow<E>

    inline fun uiStateAsCFlow(): CFlow<S> = uiState.toCFlow()

    inline fun uiEffectAsCFlow(): CFlow<E> = uiEffect.toCFlow()

    protected actual open fun onCleared() {
    }
}
