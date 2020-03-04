package com.spica27.todayhistory.model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface CallAdapterApiService {
  @GET(".")
  fun getScence1(): Deferred<Sentence>

  @GET(".")
  fun getScence2(): Deferred<Sentence>

  @GET(".")
  fun getScence3(): Deferred<Sentence>

}

class ApiSource {
  companion object {
    @JvmField
    val callAdapterInstance = Retrofit.Builder()
      .baseUrl("https://v1.hitokoto.cn/?c=a/")
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(CallAdapterApiService::class.java)
  }
}

