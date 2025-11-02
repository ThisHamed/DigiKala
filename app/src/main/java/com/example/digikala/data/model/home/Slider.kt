package com.example.digikala.data.model.home

import androidx.compose.runtime.Immutable

@Immutable
data class Slider(
    val _id: String,
    val category: String,
    val image: String,
    val priority: Int,
    val url: String
)