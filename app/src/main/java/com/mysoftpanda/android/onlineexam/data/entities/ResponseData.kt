package com.mysoftpanda.android.onlineexam.data.entities

data class ResponseData<T>(
    val status: String,
    val message: String = "Successful",
    val data: T? = null
)