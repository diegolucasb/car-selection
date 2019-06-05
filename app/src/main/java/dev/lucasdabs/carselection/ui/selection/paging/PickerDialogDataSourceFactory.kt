package dev.lucasdabs.carselection.ui.selection.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.lucasdabs.carselection.api.data.BaseData
import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.api.repository.BaseRepository
import io.reactivex.disposables.CompositeDisposable

class PickerDialogDataSourceFactory(private val service: BaseRepository,
                                    private val compositeDisposable: CompositeDisposable,
                                    private val parameters: RequestParameter)
    : DataSource.Factory<Int, BaseData>() {

    val liveData = MutableLiveData<PickerDialogDataSource>()

    override fun create(): DataSource<Int, BaseData> {
        val dataSource = PickerDialogDataSource(service, compositeDisposable, parameters)
        liveData.postValue(dataSource)
        return dataSource
    }
}