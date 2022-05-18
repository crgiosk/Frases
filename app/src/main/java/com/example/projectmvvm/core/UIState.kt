package com.example.projectmvvm.core

sealed class UIState<out T> {
    object OnLoading : UIState<Nothing>()
    class OnSuccess<T>(val data: T): UIState<T>()
    class OnError(val error: String) : UIState<Nothing>()
}