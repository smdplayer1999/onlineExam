package com.mysoftpanda.android.onlineexam.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun numberSeperator(number: String): String {


    var lastFourDigits = ""
    lastFourDigits = if (number.length > 2) {
        number.substring(number.length - 2);
    } else {
        number;
    }
    var firstTwoDigits = ""
    firstTwoDigits = if (number.length > 2) {
        number.substring(number.length - 4, number.length - 2);
    } else {
        number;
    }
    return "$firstTwoDigits/$lastFourDigits"
}
fun numberSeperate(number: String): String {


    var lastFourDigits = ""
    lastFourDigits = number.substring(number.length - 4);

    var firstFourDigits = ""
    firstFourDigits = number.substring(0, number.length - 12);
    var twoDigits = ""
    twoDigits = number.substring(number.length - 12, number.length - 10);

    return "$firstFourDigits $twoDigits** **** $lastFourDigits"
}
fun decimal(money: Long): String {
    if (money == 0.toLong()) {
        return money.toString()
    }
    val symbols = DecimalFormatSymbols()
    symbols.groupingSeparator = ' '
    symbols.zeroDigit = '0'

    symbols.decimalSeparator = ','

    val decimalFormat = DecimalFormat("#,###.00", symbols)
    return decimalFormat.format(money).toString()
}