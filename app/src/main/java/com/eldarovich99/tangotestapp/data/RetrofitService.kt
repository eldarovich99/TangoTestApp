package com.eldarovich99.tangotestapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitService {
    private val contentType = "application/json".toMediaType()

    lateinit var api: Retrofit
        private set
    private val jsonConverterFactory by lazy {
        jsonFormat.asConverterFactory(contentType)
    }

    init {
        createClient()
    }

    private fun createClient() {
        val okHttpClient = provideOkHttpClientBuilder().build()
        api = provideRetrofitClient(
            okHttpClient = okHttpClient,
            "https://radiant-inlet-94783.herokuapp.com"
        )
    }

    private fun provideOkHttpClientBuilder() = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }

    private fun provideRetrofitClient(okHttpClient: OkHttpClient, baseUrl: String) =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(jsonConverterFactory)
            .client(okHttpClient)
            .build()
}

val jsonFormat = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    isLenient = true
}