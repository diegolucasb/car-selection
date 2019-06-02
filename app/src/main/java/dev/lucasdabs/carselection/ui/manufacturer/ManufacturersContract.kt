package dev.lucasdabs.carselection.ui.manufacturer

import dev.lucasdabs.carselection.ui.BasePresenter
import dev.lucasdabs.carselection.ui.BaseView

interface ManufacturersContract {

    interface View: BaseView<Presenter> {
        fun initView()
    }

    interface Presenter: BasePresenter {
        fun getManufacturerList()
    }

}