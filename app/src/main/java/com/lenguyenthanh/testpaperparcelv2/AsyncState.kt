package com.lenguyenthanh.testpaperparcelv2

sealed class AsyncState<in T> {
    object NotStarted : AsyncState<Any>()
    object Loading : AsyncState<Any>()
    class Success<T>(val value: T) : AsyncState<T>()
    class Failure(val error: Exception) : AsyncState<Any>()

    fun isLoading(): Boolean {
        return this is Loading
    }

    fun isSuccess(): Boolean {
        return this is Success
    }
}
