package dev.lucasdabs.carselection.di

import dev.lucasdabs.carselection.api.RestClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import kotlin.reflect.KClass

object Injector {

    fun retrofitModule() = Kodein.Module("api") {
        bind<RestClient>() with singleton {
            val retrofit = RestClient()
            retrofit
        }
    }

    inline fun <reified T : Any> bindGenericService(service: KClass<T>, name: String) =
        Kodein.Module(name) {
            bind<T>() with provider {
                instance<RestClient>().buildCall(service)
            }
        }
}