package com.mysoftpanda.android.onlineexam.data.retrofit

import com.mysoftpanda.android.onlineexam.app.App
import com.mysoftpanda.android.onlineexam.data.local.LocalStorage
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient{
    val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(ChuckInterceptor(App.instance))
        .addInterceptor {
            val requestOld = it.request()
            val request = requestOld.newBuilder()
                .removeHeader("token")
                .addHeader("token", LocalStorage.instance.token)
                .method(requestOld.method, requestOld.body)
                .build()
            val response = it.proceed(request)
            response
        }
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://161.35.73.101:99/")
//        .baseUrl("https://2b5ad90acf1d.ngrok.io")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}