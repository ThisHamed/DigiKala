package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.basket.CartDetails
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.BasketRepository
import com.example.digikala.ui.screen.basket.BasketScreenState
import com.example.digikala.utils.DigitHelper.applyDiscount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val repository: BasketRepository) : ViewModel() {

    val suggestedList = MutableStateFlow<NetworkResult<List<AmazingItems>>>(NetworkResult.Loading())
    val cartDetails = MutableStateFlow(CartDetails(0, 0, 0, 0))

    private val _currentCartItems: MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val currentCartItem: StateFlow<BasketScreenState<List<CartItem>>> = _currentCartItems

    val ourCartItem: MutableStateFlow<List<CartItem>> = MutableStateFlow(emptyList())

    private val _nextCartItems: MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val nextCartItems: StateFlow<BasketScreenState<List<CartItem>>> = _nextCartItems

    val currentCartItemsCount = repository.currentCartItemsCount
    val nextCartItemsCount = repository.nextCartItemsCount

    init {
        viewModelScope.launch(Dispatchers.IO) {

            launch {
                repository.currentCartItems.collectLatest { cartItems ->
                    _currentCartItems.emit(BasketScreenState.Success(cartItems))
                    ourCartItem.emit(cartItems)
                }
            }

            launch {
                repository.nextCartItems.collectLatest { nextCartItems ->
                    _nextCartItems.emit(BasketScreenState.Success(nextCartItems))
                }
            }

            launch {
                repository.currentCartItems.collectLatest { cartItems ->
                    calculateCartDetails(cartItems)
                }
            }

        }
    }

    private fun calculateCartDetails(items: List<CartItem>) {

        var totalCount = 0
        var totalPrice = 0L
        var totalDiscount = 0L
        var payablePrice = 0L

        items.forEach { item ->
            totalPrice += item.price * item.count
            payablePrice += applyDiscount(item.price, item.discountPercent) * item.count
            totalCount += item.count
        }
        totalDiscount = totalPrice - payablePrice
        cartDetails.value = CartDetails(totalCount, totalPrice, totalDiscount, payablePrice)

    }


    fun getSuggestedItems() {
        viewModelScope.launch {
            suggestedList.emit(repository.getSuggestedItems())
        }
    }


    fun insertCartItem(cart: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCartItem(cart)
        }
    }

    fun removeCartItem(cart: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromCart(cart)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItems()
        }
    }


    fun changeCartItemCount(id: String, newCount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCartItemCount(id, newCount)
        }
    }

    fun changeCartItemStatus(id: String, newStatus: CartStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCartItemStatus(id, newStatus)
        }
    }

    fun isExist(id: String): Flow<Boolean> = repository.isExist(id)

    fun getItemCount(id: String): Flow<Int> = repository.getItemCount(id)

}
