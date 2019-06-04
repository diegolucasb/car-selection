package dev.lucasdabs.carselection.ui

import org.kodein.di.KodeinAware

interface BasePresenter : KodeinAware {

    fun onCleared()

}