package com.chuntian.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val id: Int,
    val genre: String = "Pop",
    val artist: String,
    val song: String,
    val descriptions: String,
    val imageId : Int,
    val swiped: Boolean = false
):Parcelable