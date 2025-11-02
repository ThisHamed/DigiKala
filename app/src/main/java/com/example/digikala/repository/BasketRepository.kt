package com.example.digikala.repository

import com.example.digikala.data.db.CartDao
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.BasketApiInterface
import com.example.digikala.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BasketRepository @Inject constructor(
    private val api: BasketApiInterface,
    private val dao: CartDao
) : BaseApiResponse() {

    val currentCartItems = dao.getAllItems(CartStatus.CURRENT_CART)
    val nextCartItems = dao.getAllItems(CartStatus.NEXT_CART)

    val currentCartItemsCount = dao.getCartItemsCount(CartStatus.CURRENT_CART)
    val nextCartItemsCount = dao.getCartItemsCount(CartStatus.NEXT_CART)

    fun isExist(id: String): Flow<Boolean> = dao.isExist(id)

    fun getItemCount(id: String): Flow<Int> = dao.getItemCount(id)


    suspend fun getSuggestedItems(): NetworkResult<List<AmazingItems>> =
        safeApiCall {
            api.getSuggestedItems()
        }

    suspend fun deleteAllItems() {
        dao.deleteAllItems(CartStatus.CURRENT_CART)
    }


    suspend fun insertCartItem(cart: CartItem) {
        dao.insertCartItem(cart)
    }

    suspend fun removeFromCart(cart: CartItem) {
        dao.removeItemFromCart(cart)
    }

    suspend fun changeCartItemStatus(id: String, newStatus: CartStatus) {
        dao.changeStatusCart(newStatus, id)
    }

    suspend fun changeCartItemCount(id: String, newCount: Int) {
        dao.changeCountCartItem(newCount, id)
    }

}
