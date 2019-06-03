package dev.lucasdabs.carselection.ui.selection.paging

import androidx.paging.PageKeyedDataSource
import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.api.repository.BaseRepository
import io.reactivex.disposables.CompositeDisposable

class PickerDialogDataSource(
    private val service: BaseRepository,
    private val compositeDisposable: CompositeDisposable):
    PageKeyedDataSource<Int, Manufacturer>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Manufacturer>) {
        compositeDisposable.add(
            service.fetchData(1, params.requestedLoadSize)
                .subscribe({
                    callback.onResult(
                        it.data.map { Manufacturer(it.key, it.value) },
                        it.page?.toInt(),
                        it.totalPageCount?.toInt())
                }, {

                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Manufacturer>) {
        compositeDisposable.add(
            service.fetchData(params.key, params.requestedLoadSize)
                .subscribe({
                    callback.onResult( it.data.map { Manufacturer(it.key, it.value) } , it.page?.toInt())
                }, {

                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Manufacturer>) {
    }
}