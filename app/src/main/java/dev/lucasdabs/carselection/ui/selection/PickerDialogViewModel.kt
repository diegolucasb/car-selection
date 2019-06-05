package dev.lucasdabs.carselection.ui.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.lucasdabs.carselection.api.data.BaseData
import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.api.repository.BaseRepository
import dev.lucasdabs.carselection.ui.selection.paging.PickerDialogDataSource
import dev.lucasdabs.carselection.ui.selection.paging.PickerDialogDataSourceFactory
import dev.lucasdabs.carselection.util.Constants
import io.reactivex.disposables.CompositeDisposable

class PickerDialogViewModel(repository: BaseRepository,
                            parameterName: RequestParameter) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val sourceFactory: PickerDialogDataSourceFactory by lazy {
        PickerDialogDataSourceFactory(repository, compositeDisposable, parameterName)
    }

    val list: LiveData<PagedList<BaseData>>
    val state: LiveData<PickerDialogDataSource.State>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.Pagination.PAGE_SIZE)
            .setInitialLoadSizeHint(Constants.Pagination.PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

        list = LivePagedListBuilder<Int, BaseData>(sourceFactory, config).build()
        state = Transformations
            .switchMap<PickerDialogDataSource, PickerDialogDataSource.State>(
                sourceFactory.liveData,
                PickerDialogDataSource::state)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

}