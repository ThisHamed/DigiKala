package com.example.digikala.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cart: CartItem)

    @Query("select * from shopping_cart where cartStatus=:status")
    fun getAllItems(status: CartStatus): Flow<List<CartItem>>

    @Query("DELETE FROM shopping_cart where cartStatus=:status")
    suspend fun deleteAllItems(status: CartStatus)

    @Delete
    suspend fun removeItemFromCart(item: CartItem)

    @Query("update shopping_cart set count=:newCount where itemId=:id")
    suspend fun changeCountCartItem(newCount: Int, id: String)

    @Query("update shopping_cart set cartStatus=:newCartStatus where itemId=:id")
    suspend fun changeStatusCart(newCartStatus: CartStatus, id: String)

    @Query("select total(count) as count from shopping_cart where cartStatus=:status")
    fun getCartItemsCount(status: CartStatus): Flow<Int>

    @Query("select total(count) as count from shopping_cart where itemId=:id")
    fun getItemCount(id: String): Flow<Int>

    @Query("SELECT EXISTS(SELECT * FROM shopping_cart WHERE itemId= :id)")
    fun isExist(id: String): Flow<Boolean>


}
