package com.lenguyenthanh.testpaperparcelv2

import paperparcel.PaperParcel
import paperparcel.PaperParcelable


@PaperParcel
data class TestAsyncParcel(val user: AsyncState<User>) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelTestAsyncParcel.CREATOR
    }
}