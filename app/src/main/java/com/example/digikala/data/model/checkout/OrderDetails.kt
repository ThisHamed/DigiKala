package com.example.digikala.data.model.checkout

import com.example.digikala.data.model.basket.CartItem

data class OrderDetails(
    val token: String,
    val orderAddress: String,
    val orderUserName: String,
    val orderUserPhone: String,
    val orderTotalDiscount: Long,
    val orderTotalPrice: Long,
    val orderProducts: List<CartItem>
)
