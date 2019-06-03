package dev.lucasdabs.carselection.ui.manufacturer

import android.util.Log
import dev.lucasdabs.carselection.api.impl.ManufacturerHandler
import dev.lucasdabs.carselection.api.response.BaseResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ManufacturerPresenter(private val view: ManufacturersContract.View): ManufacturersContract.Presenter {

    override val kodein by kodein(view.viewContext)
    private val handler by instance<ManufacturerHandler>()

    override fun getManufacturerList() {

        handler.fetchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t: BaseResponse? ->

            }, { t: Throwable? ->

            })

    }

}