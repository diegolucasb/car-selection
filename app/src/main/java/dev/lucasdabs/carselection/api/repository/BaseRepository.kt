package dev.lucasdabs.carselection.api.repository

import dev.lucasdabs.carselection.BuildConfig
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.util.Constants
import io.reactivex.Observable

interface BaseRepository {

    fun fetchData(page: Int = Constants.Pagination.INITIAL_PAGE,
                  pageSize: Int = Constants.Pagination.PAGE_SIZE,
                  key: String = BuildConfig.WA_KEY): Observable<BaseResponse>

}