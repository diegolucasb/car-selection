package dev.lucasdabs.carselection.ui.selection

import dev.lucasdabs.carselection.ui.BasePresenter
import dev.lucasdabs.carselection.ui.BaseView

interface PickerDialogContract {

    interface View : BaseView<Presenter>

    interface Presenter: BasePresenter

}