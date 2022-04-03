package com.eldarovich99.tangotestapp.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<T: UiState, in E: UiEvent>: ViewModel() {
    abstract val state: Flow<T>
    protected val dataScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCleared() {
        super.onCleared()
        dataScope.coroutineContext.cancelChildren()
    }
}