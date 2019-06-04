package dev.lucasdabs.carselection.api.repository

import dev.lucasdabs.carselection.BuildConfig
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.api.service.ManufacturerService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class ManufacturerRepository(private val service: ManufacturerService): BaseRepository {

    override fun fetchData(page: Int,
                           pageSize: Int,
                           key: String): Observable<BaseResponse> =
            service.fetchData(
                page = page,
                pageSize = pageSize,
                key = BuildConfig.WA_KEY).subscribeOn(Schedulers.newThread())

}