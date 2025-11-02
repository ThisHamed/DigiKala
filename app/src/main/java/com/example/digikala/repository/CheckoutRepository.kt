package com.example.digikala.repository

import com.example.digikala.data.model.checkout.ConfirmPurchase
import com.example.digikala.data.model.checkout.OrderDetails
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.model.home.Category
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.CheckoutApiInterface
import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProfileApiInterface
import javax.inject.Inject

class CheckoutRepository @Inject constructor(
    private val api: CheckoutApiInterface
) : BaseApiResponse() {

    suspend fun getShippingCost(address: String): NetworkResult<Int> =
        safeApiCall {
            api.getShippingCost(address)
        }

    suspend fun setNewOrder(cartOrderDetail: OrderDetails): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(cartOrderDetail)
        }


    suspend fun confirmPurchase(confirmPurchase: ConfirmPurchase): NetworkResult<String> =
        safeApiCall {
            api.confirmPurchase(confirmPurchase)
        }

}