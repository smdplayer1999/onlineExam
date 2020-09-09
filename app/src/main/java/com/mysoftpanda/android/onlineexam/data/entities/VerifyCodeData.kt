package com.mysoftpanda.android.onlineexam.data.entities

data class VerifyCodeData(
    val status: String,
    val message: String = "Successful",
    val data: Any
)