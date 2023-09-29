package com.adrianotelesc.notes.core

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel as AndroidXViewModel
import androidx.lifecycle.viewModelScope as androidXViewModelScope

actual abstract class ViewModel<S: Any, E: Any> : AndroidXViewModel() {
    actual val viewModelScope get() = androidXViewModelScope

    actual abstract val uiState: StateFlow<S>

    actual abstract val sideEffect: SharedFlow<E>

    actual override fun onCleared() {
        super.onCleared()
    }
}
