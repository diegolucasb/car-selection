package dev.lucasdabs.carselection.ui.manufacturer

import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.api.repository.ManufacturerRepository
import dev.lucasdabs.carselection.api.response.BaseResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ManufacturerPresenter(private val view: ManufacturersContract.View): ManufacturersContract.Presenter {

    override val kodein by kodein(view.viewContext)
    private val repository by instance<ManufacturerRepository>()

    override fun getManufacturerList() {

        repository.fetchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onFetchSuccessful, ::onFetchError)

    }

    private fun onFetchSuccessful(baseResponse: BaseResponse) {
        view.addSpinnerData(baseResponse.data.map { Manufacturer(it.key, it.value) })
    }

    private fun onFetchError(throwable: Throwable) {
    }

    override fun onCleared() {
    }


}