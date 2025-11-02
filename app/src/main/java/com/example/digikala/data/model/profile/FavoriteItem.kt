package com.example.digikala.data.model.profile

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikala.utils.Constants.FAVORITE_LIST_TABLE

@Entity(tableName = FAVORITE_LIST_TABLE)
data class FavoriteItem(
   @PrimaryKey
    val id:String,
    val discountPercent:Int,
    val image:String,
    val name:String,
    val price:Long,
    val seller:String,
    val star:Double
)
