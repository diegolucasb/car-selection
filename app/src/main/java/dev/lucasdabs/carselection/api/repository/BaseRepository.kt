package dev.lucasdabs.carselection.api.repository

import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.api.response.BaseResponse
import io.reactivex.Observable

interface BaseRepository {
    fun fetchData(parameters: RequestParameter): Observable<BaseResponse>
}