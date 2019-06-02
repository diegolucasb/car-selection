package dev.lucasdabs.carselection.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dev.lucasdabs.carselection.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

class RestClient {

    private val retrofit: Retrofit by lazy {
        buildRetrofit(
            baseUrl = BuildConfig.API_URL,
            httpClient = getHttpClient(getInterceptor()),
            jsonConverter = getJsonConverter()
        )
    }

    private fun getInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = BuildConfig.LOG_LEVEL }
    }

    private fun getHttpClient(interceptor: HttpLoggingInterceptor,
                              readTimeout: Long = DEFAULT_TIMEOUT,
                              connectionTimeout: Long = DEFAULT_TIMEOUT
    ): OkHttpClient {

        return OkHttpClient().newBuilder().apply {
            readTimeout(readTimeout, TimeUnit.SECONDS)
            connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                chain.proceed(request.build())
            }
            addInterceptor(interceptor)
        }.build()
    }

    private fun getJsonConverter(): Gson = GsonBuilder()
        .create()

    private fun buildRetrofit(baseUrl: String,
                              httpClient: OkHttpClient,
                              jsonConverter: Gson
    ): Retrofit {

        return Retrofit.Builder().run {
            baseUrl(baseUrl)
            client(httpClient)
            addConverterFactory(GsonConverterFactory.create(jsonConverter))
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            build()
        }
    }

    fun <T : Any> buildCall(kClass: KClass<T>): T = retrofit.create(kClass.java)

    companion object {
        private const val DEFAULT_TIMEOUT = 60L
    }
}