package com.paypay.xChange.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey
    val code: String,
    val name: String? = null,
    val rate: Double = 0.0 // Based on USD
)
