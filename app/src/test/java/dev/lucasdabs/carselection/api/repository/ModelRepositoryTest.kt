package dev.lucasdabs.carselection.api.repository

import dev.lucasdabs.carselection.api.data.RequestParameter
import dev.lucasdabs.carselection.api.response.BaseResponse
import dev.lucasdabs.carselection.api.service.BuiltDatesService
import dev.lucasdabs.carselection.api.service.ModelService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class ModelRepositoryTest {

    private lateinit var service: BuiltDatesService
    private lateinit var repository: BuiltDateRepository

    private lateinit var parameter: RequestParameter
    private lateinit var response: Observable<BaseResponse>

    @Before
    fun setup() {
        service = mockk()
        parameter = mockk(relaxed = true)
        response = mockk(relaxed = true)
        repository = BuiltDateRepository(service)
    }

    @Test
    fun `test if fetch method is properly called`() {
        every { service.fetchData( mapOf() ) } returns response
        service.fetchData(mapOf())
        verify { service.fetchData(mapOf()) }
    }
}