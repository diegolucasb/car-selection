package dev.lucasdabs.carselection.api

import dev.lucasdabs.carselection.api.service.ManufacturerService
import org.junit.Assert
import org.junit.Test

class RestClientTest {

    @Test
    fun `test if restClient service instance is not null`() {
        val api = RestClient()
        val service = api.buildCall(ManufacturerService::class)

        Assert.assertNotNull(service)
    }

}