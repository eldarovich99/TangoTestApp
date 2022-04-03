package com.eldarovich99.tangotestapp.mvi

import com.eldarovich99.tangotestapp.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<S : UiState, E : UiEvent>(initialVal: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialVal)
    val state: StateFlow<S>
        get() = _state

    val timeCapsule: TimeCapsule<S> = TimeTravelCapsule { storedState ->
        _state.value = storedState
    }

    init {
        timeCapsule.addState(initialVal)
    }

    fun sendEvent(event: E) {
        reduce(_state.value, event)
    }

    fun setState(newState: S) {
        _state.value = newState

        if (BuildConfig.DEBUG) {
            timeCapsule.addState(newState)
        }
    }

    abstract fun reduce(oldState: S, event: E)
}
interface UiState

interface UiEvent