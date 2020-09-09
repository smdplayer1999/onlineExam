package com.mysoftpanda.android.onlineexam.intro

import java.io.Serializable

data class IntoData(
    var header: String,
    var image: Int,
    var background: Int,
    var text: String
) : Serializable