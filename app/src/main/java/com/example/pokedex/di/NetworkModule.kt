package com.example.pokedex.di

import com.example.pokedex.network.PokeDexService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val BASE_URL_GET = "https://pokeapi.co/api/v2/"

fun provideHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()

    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun provideConverterFactory(): GsonConverterFactory =
    GsonConverterFactory.create()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {

    return Retrofit.Builder()
        .baseUrl(BASE_URL_GET)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideService(retrofit: Retrofit): PokeDexService =
    retrofit.create(PokeDexService::class.java)

val networkModule = module {
    single { provideHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(), get()) }
    single { provideService(get()) }
}