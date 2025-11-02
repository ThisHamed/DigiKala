package com.example.digikala.data.model.home

import androidx.compose.runtime.Immutable

@Immutable
data class AmazingItems(
    val _id: String,
    val name: String,
    val seller: String,
    val price: Long,
    val discountPercent: Int,
    val image: String
)