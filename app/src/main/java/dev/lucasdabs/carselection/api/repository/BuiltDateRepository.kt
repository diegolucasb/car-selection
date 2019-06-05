package dev.lucasdabs.carselection.api.repository

import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.api.service.BuiltDatesService
import dev.lucasdabs.carselection.api.service.ModelService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class BuiltDateRepository(private val service: BuiltDatesService): BaseRepository {

    override fun fetchData(parameters: RequestParameter): Observable<BaseResponse> {
        val map = mapOf(
            parameters.key.first to parameters.key.second,
            parameters.manufacturerId?.first to parameters.manufacturerId?.second?.id,
            parameters.modelId?.first to parameters.modelId?.second?.id
        )

        return service.fetchData(map).subscribeOn(Schedulers.newThread())
    }

}