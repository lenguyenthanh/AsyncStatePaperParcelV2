package com.lenguyenthanh.testpaperparcelv2

import android.os.Parcel
import paperparcel.RegisterAdapter
import paperparcel.TypeAdapter

@RegisterAdapter
class AsyncTypeAdapter<T>(val valueTypeAdapter: TypeAdapter<T>) : TypeAdapter<@JvmSuppressWildcards AsyncState<T>> {
    override fun readFromParcel(source: Parcel): AsyncState<T> {
        val state = source.readLong()
        if (state == 0L) {
            return AsyncState.Loading as AsyncState<T>
        }
        else if (state == 2L) {
            val value = valueTypeAdapter.readFromParcel(source)
            return AsyncState.Success(value)
        } else if(state == 1L) {
            return AsyncState.Failure(RuntimeException("Recover from parcel")) as AsyncState<T>
        }
        return AsyncState.NotStarted as AsyncState<T>
    }

    override fun writeToParcel(value: AsyncState<T>, dest: Parcel, flags: Int) {
        when (value) {
            is AsyncState.Loading -> dest.writeLong(0L)
            is AsyncState.Failure -> dest.writeLong(1L)
            is AsyncState.Success -> {
                dest.writeLong(2L)
                valueTypeAdapter.writeToParcel(value.value, dest, flags)
            }
            is AsyncState.NotStarted -> dest.writeLong(3L)
        }
    }
}
