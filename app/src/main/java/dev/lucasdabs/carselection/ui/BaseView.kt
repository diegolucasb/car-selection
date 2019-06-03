package dev.lucasdabs.carselection.ui

import android.content.Context

interface BaseView<T : BasePresenter> {

    val presenter: T
    val viewContext: Context

    fun initProgress()
    fun stopProgress()
    fun showError(errorMessage: String)
}