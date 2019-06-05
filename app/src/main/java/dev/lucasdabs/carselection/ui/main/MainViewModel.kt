package dev.lucasdabs.carselection.ui.main

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.lucasdabs.carselection.R
import dev.lucasdabs.carselection.api.data.BaseData
import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.ui.selection.PickerDialogFragment
import dev.lucasdabs.carselection.util.RequestType

class MainViewModel: ViewModel() {

    val manufacturer = MutableLiveData<BaseData>()
    val model = MutableLiveData<BaseData>()
    val year = MutableLiveData<BaseData>()

    fun bundle(viewId: Int) =
        when(viewId) {

            R.id.editManufacturer -> {
                bundleOf(
                    PickerDialogFragment.PARAMETER to RequestParameter(),
                    PickerDialogFragment.SERVICE to RequestType.MANUFACTURER
                )
            }
            R.id.editModel -> {
                bundleOf(
                    PickerDialogFragment.PARAMETER to RequestParameter().apply {
                        manufacturer.value?.let {
                            manufacturerId = "manufacturer" to it
                        }
                    },
                    PickerDialogFragment.SERVICE to RequestType.MODEL
                )
            }
            R.id.editYear -> {
                bundleOf(
                    PickerDialogFragment.PARAMETER to RequestParameter().apply {
                        model.value?.let {
                            manufacturerId = "manufacturer" to manufacturer.value!!
                            modelId = "main-type" to it
                        }
                    },
                    PickerDialogFragment.SERVICE to RequestType.BUILT_DATES
                )
            }
            else -> null
        }


    fun postValue(viewId: Int, baseData: BaseData) {
        when(viewId) {
            R.id.editManufacturer -> manufacturer.postValue(baseData)
            R.id.editModel -> model.postValue(baseData)
            R.id.editYear -> year.postValue(baseData)
        }
    }
}