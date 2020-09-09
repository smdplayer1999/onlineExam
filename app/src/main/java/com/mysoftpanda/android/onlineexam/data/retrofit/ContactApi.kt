package com.mysoftpanda.android.onlineexam.data.retrofit

import com.mysoftpanda.android.onlineexam.data.entities.*
import retrofit2.Call
import retrofit2.http.*

interface ContactApi {
    /**
     * 1. Get all contacts
     * */
    @GET("contact")
//    @Headers("lang:uz")
    fun getAll(): Call<ResponseData<List<ContactReturnedData>>>

    /**
     * 2. add new a contact
     * */
    @POST("contact")
    fun add(@Body contactData: ContactData): Call<ResponseData<ContactReturnedData>>

    /**
     * 3. remove a contact
     * */
    @HTTP(method = "DELETE", path = "contact", hasBody = true)
    fun remove(@Body contactData: ContactData): Call<ResponseData<ContactReturnedData>>
/**
 * 4 Update contact
 * */
    @PUT("contact")
    fun update(@Body contactData: ContactData): Call<ResponseData<ContactReturnedData>>
}