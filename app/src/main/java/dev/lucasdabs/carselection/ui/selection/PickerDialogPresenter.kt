package dev.lucasdabs.carselection.ui.selection

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.api.repository.ManufacturerRepository
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.ui.selection.paging.PickerDialogDataSourceFactory
import dev.lucasdabs.carselection.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PickerDialogPresenter(private val view: PickerDialogContract.View): PickerDialogContract.Presenter {

    override val kodein by kodein(view.viewContext)
    private val manufacturerRepository by instance<ManufacturerRepository>()

    private val compositeDisposable = CompositeDisposable()
    private val sourceFactory: PickerDialogDataSourceFactory by lazy {
        PickerDialogDataSourceFactory(manufacturerRepository, compositeDisposable)
    }

    val list: LiveData<PagedList<Manufacturer>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.Pagination.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.Pagination.PAGE_SIZE * 2)
            .setEnablePlaceholders(false)
            .build()

        list = LivePagedListBuilder<Int, Manufacturer>(sourceFactory, config).build()
    }

    override fun loadData(currentPage: Int) {
//        manufacturerRepository.fetchData()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(::onFetchSuccessful, ::onFetchError)
    }

    private fun onFetchSuccessful(baseResponse: BaseResponse) {
        val listManufacturer = baseResponse.data.map { Manufacturer(it.key, it.value) }
        view.updateAdapter(listManufacturer)
        view.stopProgress()
    }

    private fun onFetchError(throwable: Throwable) {
//        view.showError(throwable.message?:"")
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }


}