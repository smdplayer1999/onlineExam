package com.mysoftpanda.android.onlineexam.data.entities

import androidx.recyclerview.widget.DiffUtil

data class ContactReturnedData(
    val id: Int = 0,
    var phoneNumber: String,
    var lastName: String,
    var firstName: String
)