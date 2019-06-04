package dev.lucasdabs.carselection.ui.manufacturer

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.lucasdabs.carselection.R
import dev.lucasdabs.carselection.api.data.Manufacturer
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*


class ManufacturerActivity : AppCompatActivity(), ManufacturersContract.View {

    override val viewContext: Context = this

    override val presenter by lazy { ManufacturerPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        presenter.getManufacturerList()
    }

    override fun initView() {

    }

    override fun addSpinnerData(list: List<Manufacturer>) {
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter
    }

}