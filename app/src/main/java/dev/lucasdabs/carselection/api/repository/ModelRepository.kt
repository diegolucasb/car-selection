package dev.lucasdabs.carselection.api.repository

import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.api.service.ModelService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class ModelRepository(private val service: ModelService): BaseRepository {

    override fun fetchData(parameters: RequestParameter): Observable<BaseResponse> {
        val map = mapOf(
            parameters.page.first to parameters.page.second.toString(),
            parameters.pageSize.first to parameters.pageSize.second.toString(),
            parameters.key.first to parameters.key.second,
            parameters.manufacturerId?.first to parameters.manufacturerId?.second?.id
        )

        return service.fetchData(map).subscribeOn(Schedulers.newThread())
    }

}