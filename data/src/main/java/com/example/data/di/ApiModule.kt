package com.example.data.di

import com.example.data.api.UserApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { get<Retrofit>().create(UserApi::class.java) }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("app-id", "61154ce1e8ae9f19e7dd7778")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
            .callTimeout(20, TimeUnit.SECONDS)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://dummyapi.io/data/v1/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
