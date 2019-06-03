package dev.lucasdabs.carselection.ui

import android.content.Intent
import dev.lucasdabs.carselection.R

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import dev.lucasdabs.carselection.api.RestClient
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.api.service.ManufacturerService
import dev.lucasdabs.carselection.ui.selection.PickerDialogFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editManufacturer.setOnClickListener(::openPickerDialog)
    }

    private fun openPickerDialog(view: View) {
        val pickerDialog = PickerDialogFragment.newInstance("Manufacturer")
        pickerDialog.show(supportFragmentManager, "tag")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
