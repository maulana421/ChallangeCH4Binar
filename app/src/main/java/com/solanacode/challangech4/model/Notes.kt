package com.solanacode.challangech4.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val judul : String,
    val desc : String
): Serializable