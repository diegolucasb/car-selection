package dev.lucasdabs.carselection.ui.selection

import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.api.repository.ManufacturerRepository
import dev.lucasdabs.carselection.api.response.BaseResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PickerDialogPresenter(private val view: PickerDialogContract.View): PickerDialogContract.Presenter {

    override val kodein by kodein(view.viewContext)
    private val manufacturerHandler by instance<ManufacturerRepository>()

    override fun loadData(currentPage: Int) {
        manufacturerHandler.fetchData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onFetchSuccessful, ::onFetchError)
    }

    private fun onFetchSuccessful(baseResponse: BaseResponse) {
        val listManufacturer = baseResponse.data.map { Manufacturer(it.key, it.value) }
        view.updateAdapter(listManufacturer)
        view.stopProgress()
    }

    private fun onFetchError(throwable: Throwable) {
//        view.showError(throwable.message?:"")
    }

}