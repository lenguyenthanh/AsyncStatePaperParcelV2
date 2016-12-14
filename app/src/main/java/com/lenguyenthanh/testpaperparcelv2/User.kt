package com.lenguyenthanh.testpaperparcelv2

import paperparcel.PaperParcel
import paperparcel.PaperParcelable


@PaperParcel
data class User(
        val id: String,
        val givenNames: String,
        val lastName: String? = null
) : PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelUser.CREATOR
    }
}