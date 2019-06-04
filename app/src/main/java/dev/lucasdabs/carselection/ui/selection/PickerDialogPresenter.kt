package dev.lucasdabs.carselection.ui.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.api.repository.ManufacturerRepository
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.ui.selection.paging.PickerDialogDataSource
import dev.lucasdabs.carselection.ui.selection.paging.PickerDialogDataSourceFactory
import dev.lucasdabs.carselection.util.Constants
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PickerDialogPresenter(view: PickerDialogContract.View): PickerDialogContract.Presenter {

    override val kodein by kodein(view.viewContext)
    private val manufacturerRepository by instance<ManufacturerRepository>()

    private val compositeDisposable = CompositeDisposable()
    private val sourceFactory: PickerDialogDataSourceFactory by lazy {
        PickerDialogDataSourceFactory(manufacturerRepository, compositeDisposable)
    }

    val list: LiveData<PagedList<Manufacturer>>
    val state: LiveData<PickerDialogDataSource.State>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.Pagination.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.Pagination.PAGE_SIZE * 2)
            .setEnablePlaceholders(false)
            .build()

        list = LivePagedListBuilder<Int, Manufacturer>(sourceFactory, config).build()
        state = Transformations
            .switchMap<PickerDialogDataSource, PickerDialogDataSource.State>(
                sourceFactory.liveData,
                PickerDialogDataSource::state)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}