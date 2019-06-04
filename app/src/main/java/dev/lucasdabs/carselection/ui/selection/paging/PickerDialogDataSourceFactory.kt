package dev.lucasdabs.carselection.ui.selection.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.lucasdabs.carselection.api.data.Manufacturer
import dev.lucasdabs.carselection.api.repository.BaseRepository
import io.reactivex.disposables.CompositeDisposable

class PickerDialogDataSourceFactory(private val service: BaseRepository,
                                    private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Manufacturer>() {

    val liveData = MutableLiveData<PickerDialogDataSource>()

    override fun create(): DataSource<Int, Manufacturer> {
        val dataSource = PickerDialogDataSource(service, compositeDisposable)
        liveData.postValue(dataSource)
        return dataSource
    }

}