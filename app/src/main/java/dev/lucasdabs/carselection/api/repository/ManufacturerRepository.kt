package dev.lucasdabs.carselection.api.repository

import dev.lucasdabs.carselection.api.service.ManufacturerService
import io.reactivex.schedulers.Schedulers

class ManufacturerRepository(private val service: ManufacturerService) {

    fun fetchData(currentPage: Int = 0) = service.getList(page = currentPage).subscribeOn(Schedulers.newThread())

}