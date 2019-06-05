package dev.lucasdabs.carselection.ui.selection.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import dev.lucasdabs.carselection.api.data.BaseData
import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.api.repository.BaseRepository
import io.reactivex.disposables.CompositeDisposable

class PickerDialogDataSource(
    private val service: BaseRepository,
    private val compositeDisposable: CompositeDisposable,
    private val parameters: RequestParameter) :
    PageKeyedDataSource<Int, BaseData>() {

    val state: MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, BaseData>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            service.fetchData(parameters.apply {
                pageSize = "pageSize" to params.requestedLoadSize
            })
                .subscribe({
                    updateState(State.DONE)

                    callback.onResult(
                        it.data.map { item -> BaseData(item.key, item.value) },
                        it.page,
                        it.page?.plus(1))
                }, {
                    updateState(State.ERROR)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BaseData>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            service.fetchData(parameters.apply {
                page = "page" to params.key
                pageSize = "pageSize" to params.requestedLoadSize
            })
                .subscribe({
                    updateState(State.DONE)

                    callback.onResult( it.data.map {
                            item -> BaseData(item.key, item.value) },
                        it.page?.plus(1))
                }, {
                    updateState(State.ERROR)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BaseData>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    enum class State {
        DONE, LOADING, ERROR
    }
}
