package dev.lucasdabs.carselection.extension

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.colorRes(colorRes: Int) =
    ContextCompat.getColor(this, colorRes)