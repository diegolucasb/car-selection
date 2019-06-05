package dev.lucasdabs.carselection.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.lucasdabs.carselection.R
import dev.lucasdabs.carselection.ui.selection.PickerDialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders
            .of(this)
            .get(MainViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        editManufacturer.setOnClickListener(::openPickerDialog)
        editModel.setOnClickListener(::openPickerDialog)
        editYear.setOnClickListener(::openPickerDialog)

        editManufacturer.isEnabled = true
        editModel.isEnabled = false
        editYear.isEnabled = false

        viewModel.manufacturer.observe(this, Observer {
            editModel.isEnabled = it != null
            editManufacturer.setText(it.name)

            editModel.text.clear()
            editYear.text.clear()
        })

        viewModel.model.observe(this, Observer {
            editYear.isEnabled = it != null
            editModel.setText(it.name)
            editYear.text.clear()
        })

        viewModel.year.observe(this, Observer {
            editYear.setText(it.name)
        })
    }

    private fun openPickerDialog(view: View) {
        val bundle = viewModel.bundle(view.id)

        val pickerDialog = PickerDialogFragment.newInstance("dialog")

        bundle?.let { pickerDialog.arguments = it }
        pickerDialog.show(supportFragmentManager, "tag")
        pickerDialog.manufacturerLiveData.observe(this, Observer {
            viewModel.postValue(view.id, it)
        })
    }
}
