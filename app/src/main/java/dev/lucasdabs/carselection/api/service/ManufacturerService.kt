package dev.lucasdabs.carselection.api.service

import dev.lucasdabs.carselection.api.response.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ManufacturerService {

    @GET("/v1/car-types/manufacturer")
    fun fetchData(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("wa_key") key: String): Observable<BaseResponse>
}