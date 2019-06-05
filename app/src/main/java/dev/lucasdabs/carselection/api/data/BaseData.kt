package dev.lucasdabs.carselection.api.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseData(val id: String, val name: String): Parcelable {
    override fun toString(): String = name
}