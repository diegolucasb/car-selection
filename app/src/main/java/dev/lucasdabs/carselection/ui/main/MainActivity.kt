package dev.lucasdabs.carselection.ui.main

import dev.lucasdabs.carselection.R

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.ui.selection.PickerDialogFragment
import dev.lucasdabs.carselection.util.RequestType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editManufacturer.setOnClickListener(::openPickerDialog)
    }

    private fun openPickerDialog(view: View) {
        val pickerDialog = PickerDialogFragment.newInstance("Manufacturer")

        val bundle = bundleOf(
            PickerDialogFragment.PARAMETER to RequestParameter().apply {
                manufacturerId = "manufacturer" to "160"
                modelId = "main-type" to "Alero"
            },
            PickerDialogFragment.SERVICE to RequestType.MANUFACTURER
        )

        pickerDialog.arguments = bundle
        pickerDialog.show(supportFragmentManager, "tag")
        pickerDialog.manufacturerLiveData.observe(this, Observer {
            editManufacturer.setText(it.name)
        })
    }

}
