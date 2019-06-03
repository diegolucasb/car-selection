package dev.lucasdabs.carselection.core

import android.app.Application
import dev.lucasdabs.carselection.api.impl.ManufacturerHandler
import dev.lucasdabs.carselection.api.service.ManufacturerService
import dev.lucasdabs.carselection.di.Injector
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class App: Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(Injector.bindGenericService(ManufacturerService::class))
        bind<ManufacturerHandler>() with provider { ManufacturerHandler(instance()) }
    }
}