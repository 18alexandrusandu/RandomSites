package com.example.jokeapp.api.Data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

val piuApi=PiuApi.create()
const val contentType="Content-Type:application/json"
interface PiuApi
{

    @Headers(contentType)
    @GET("Any?amount=10")
    suspend fun readAny():ResponseJokes

    @Headers(contentType)
    @GET("Christmas?amount=10")
    suspend fun readChristmas():ResponseJokes


    @Headers(contentType)
    @GET("Programming?amount=10")
    suspend fun readProgramming():ResponseJokes


    @Headers(contentType)
    @GET("Misc?amount=10")
    suspend fun readMisc():ResponseJokes

    @Headers(contentType)
    @GET("Dark?amount=10")
    suspend fun readDark():ResponseJokes

    @Headers(contentType)
    @GET("Pun?amount=10")
    suspend  fun readPun():ResponseJokes

  @Headers(contentType)
  @GET("Spooky?amount=10")
  suspend fun readSpooky():ResponseJokes


companion object{
        private val logger=HttpLoggingInterceptor().apply{
            level=HttpLoggingInterceptor.Level.BODY
        }
        private val httpClient= OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()
        fun create():PiuApi=Retrofit.Builder()
            .baseUrl("https://sv443.net/jokeapi/v2/joke/")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PiuApi::class.java)

            }
}