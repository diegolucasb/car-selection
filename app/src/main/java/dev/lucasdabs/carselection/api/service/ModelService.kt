package dev.lucasdabs.carselection.api.service

import dev.lucasdabs.carselection.api.response.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ModelService {

    @GET("/v1/car-types/main-types")
    fun fetchData(
        @QueryMap args: Map<String?, String?>): Observable<BaseResponse>
}