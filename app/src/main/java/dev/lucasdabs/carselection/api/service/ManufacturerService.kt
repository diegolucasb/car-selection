package dev.lucasdabs.carselection.api.service

import dev.lucasdabs.carselection.api.repository.BaseRepository
import dev.lucasdabs.carselection.api.response.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ManufacturerService: BaseRepository {

    @GET("/v1/car-types/manufacturer")
    override fun fetchData(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("wa_key") key: String): Observable<BaseResponse>

//    @GET("/v1/car-types/manufacturer")
//    fun getList(
//        @Query("page") page: Int = Constants.Pagination.INITIAL_PAGE,
//        @Query("pageSize") pageSize: Int = Constants.Pagination.PAGE_SIZE,
//        @Query("wa_key") key: String = BuildConfig.WA_KEY): Observable<BaseResponse>
}