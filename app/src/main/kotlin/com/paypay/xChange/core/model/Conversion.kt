package com.paypay.xChange.core.model

data class Conversion(
    val from:Currency,
    val to:Currency,
    val fromValue:Double, // Value of conversion
    val toValue:Double, // Value to be converted
    val result:Double // Converted value
)
