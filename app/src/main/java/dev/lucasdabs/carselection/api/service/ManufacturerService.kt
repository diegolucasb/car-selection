package dev.lucasdabs.carselection.api.service

import dev.lucasdabs.carselection.BuildConfig
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ManufacturerService {

    @GET("/v1/car-types/manufacturer")
    fun getList(
        @Query("page") page: Int = Constants.Pagination.INITIAL_PAGE,
        @Query("pageSize") pageSize: Int = Constants.Pagination.PAGE_SIZE,
        @Query("wa_key") key: String = BuildConfig.WA_KEY): Observable<BaseResponse>
}