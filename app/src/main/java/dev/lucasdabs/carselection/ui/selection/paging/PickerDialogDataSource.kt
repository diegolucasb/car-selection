package dev.lucasdabs.carselection.ui.selection.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.api.repository.BaseRepository
import dev.lucasdabs.carselection.util.Constants
import io.reactivex.disposables.CompositeDisposable

class PickerDialogDataSource(
    private val service: BaseRepository,
    private val compositeDisposable: CompositeDisposable):
    PageKeyedDataSource<Int, Manufacturer>() {

    val state: MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Manufacturer>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            service.fetchData(Constants.Pagination.INITIAL_PAGE, params.requestedLoadSize)
                .subscribe({
                    updateState(State.DONE)

                    callback.onResult(
                        it.data.map { item -> Manufacturer(item.key, item.value) },
                        it.page,
                        it.page?.plus(1))
                }, {
                    updateState(State.ERROR)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Manufacturer>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            service.fetchData(params.key, params.requestedLoadSize)
                .subscribe({
                    updateState(State.DONE)

                    callback.onResult( it.data.map {
                            item -> Manufacturer(item.key, item.value) } ,
                        it.page?.plus(1))
                }, {
                    updateState(State.ERROR)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Manufacturer>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    enum class State {
        DONE, LOADING, ERROR
    }
}

