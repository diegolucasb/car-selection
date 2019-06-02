package dev.lucasdabs.carselection.api.impl

import dev.lucasdabs.carselection.api.service.ManufacturerService
import io.reactivex.schedulers.Schedulers

class ManufacturerHandler(private val service: ManufacturerService) {

    fun fetchData() = service.getList().observeOn(Schedulers.io())

}