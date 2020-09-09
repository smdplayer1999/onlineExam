package com.mysoftpanda.android.onlineexam.data.entities

data class CardData(
    var id: Int = 0,
    val phoneNumber: String? = null,
    val pan: String?,//8600 1234 1234 1234 -> 8600123412341234
    val expiredAt: String?,//05/21
    val name: String?,// mening kartam
    val color: Int? = null,//0-6
    val amount: Long//Balance
)