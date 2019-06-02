package dev.lucasdabs.carselection.ui

import dev.lucasdabs.carselection.R

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dev.lucasdabs.carselection.api.RestClient
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.api.service.ManufacturerService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RestClient().buildCall(ManufacturerService::class).getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t: BaseResponse? ->
                Log.i("auto", t.toString())
            }, { t: Throwable? ->
                Log.e("auto", t.toString())
            })
    }
}
