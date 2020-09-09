package com.mysoftpanda.android.onlineexam.data.retrofit


import com.mysoftpanda.android.onlineexam.data.entities.*

import retrofit2.Call
import retrofit2.http.*

interface AuthApi {

    /**
     * Register User
     * */
    @POST("contact/register")
    fun registerUser(@Body registerUserData: RegisterUserData): Call<RegisterData>

    /**
     * Verify PhoneNumber
     * */
    @POST("contact/verify")
    fun verifyPhoneNumber(@Body verifyCodeUserData: VerifyCodeUserData): Call<ResponseData<Any>>

    @POST("contact/login")
    fun login(@Body verifyCodeUserData: LoginPasswordData): Call<ResponseData<Any>>

    @POST("contact/reset")
    fun  reset(@Body phoneNumber: PhoneData): Call<RegisterData>

    @POST("contact/password")
    fun resetPassword(@Body phoneNumber: ResetPasswordData): Call<ResponseData<Any>>

    @POST("contact/resend")
    fun resend(@Body phoneNumber: PhoneData): Call<RegisterData>

    @POST("contact/remove")
    fun remove(@Body verifyCodeUserData: LoginPasswordData): Call<ResponseData<Any>>


}