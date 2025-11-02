package com.example.digikala.data.model.basket

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikala.utils.Constants.BASKET_TABLE

@Entity(tableName = BASKET_TABLE)
data class CartItem(
    @PrimaryKey
    val itemId: String,
    val name: String,
    val seller: String,
    val price: Long,
    val discountPercent: Int,
    val image: String,
    val count: Int,
    val cartStatus: CartStatus
)
