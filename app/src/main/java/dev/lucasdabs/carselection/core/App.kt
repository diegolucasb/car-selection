package dev.lucasdabs.carselection.core

import android.app.Application
import android.content.Context
import dev.lucasdabs.carselection.api.repository.BaseRepository
import dev.lucasdabs.carselection.api.repository.BuiltDateRepository
import dev.lucasdabs.carselection.api.repository.ManufacturerRepository
import dev.lucasdabs.carselection.api.repository.ModelRepository
import dev.lucasdabs.carselection.api.service.BuiltDatesService
import dev.lucasdabs.carselection.api.service.ManufacturerService
import dev.lucasdabs.carselection.api.service.ModelService
import dev.lucasdabs.carselection.di.Injector
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class App: Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(Injector.retrofitModule())
        import(Injector.bindGenericService(ManufacturerService::class, "manufacturerService"))
        import(Injector.bindGenericService(ModelService::class, "modelService"))
        import(Injector.bindGenericService(BuiltDatesService::class, "builtDateService"))

        //repository
        bind<ManufacturerRepository>() with provider { ManufacturerRepository(instance()) }
        bind<ModelRepository>() with provider { ModelRepository(instance()) }
        bind<BuiltDateRepository>() with provider { BuiltDateRepository(instance()) }
        bind<Context>() with provider { applicationContext }
    }
}