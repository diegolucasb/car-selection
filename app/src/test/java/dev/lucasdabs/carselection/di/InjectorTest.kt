package dev.lucasdabs.carselection.di

import dev.lucasdabs.carselection.api.RestClient
import dev.lucasdabs.carselection.api.repository.ManufacturerRepository
import dev.lucasdabs.carselection.api.service.ManufacturerService
import org.junit.Assert
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class InjectorTest {

    private val kodein = Kodein {
        import(Injector.retrofitModule())
        Injector.bindGenericService(ManufacturerService::class, "testService")
        bind<ManufacturerRepository>() with provider { ManufacturerRepository(instance()) }
    }

    @Test
    fun `test if restClient instance should not be null`() {
        val instance by kodein.instance<RestClient>()
        Assert.assertNotNull(instance)
    }
}