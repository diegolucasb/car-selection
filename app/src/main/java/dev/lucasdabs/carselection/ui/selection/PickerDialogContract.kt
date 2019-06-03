package dev.lucasdabs.carselection.ui.selection

import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.ui.BasePresenter
import dev.lucasdabs.carselection.ui.BaseView
import dev.lucasdabs.carselection.ui.selection.presentation.PickerDialogAdapter
import io.reactivex.Observable

interface PickerDialogContract {

    interface View: BaseView<Presenter> {
        fun updateAdapter(list: List<Manufacturer>)

    }

    interface Presenter: BasePresenter {
        fun loadData(currentPage: Int = 0)
    }

}