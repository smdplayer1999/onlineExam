package com.mysoftpanda.android.onlineexam.data.retrofit

import com.mysoftpanda.android.onlineexam.data.entities.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CardApi {
    /**
     * Verify PhoneNumber
     * */
    @POST("card/verify")
    fun verifyCard(@Body verifyCodeUserData: VerifyCard): Call<ResponseData<CardData>>
    /**
     * 2. add new a contact
     * */
    @POST("card")
    fun addCard(@Body contactData: AddCardData): Call<ResponseData<Any>>
    @GET("card")
//    @Headers("lang:uz")
    fun getAllCard(): Call<ResponseData<List<CardData>>>
}