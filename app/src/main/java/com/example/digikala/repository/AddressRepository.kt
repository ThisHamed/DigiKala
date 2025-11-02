package com.example.digikala.repository

import com.example.digikala.data.db.CartDao
import com.example.digikala.data.model.address.UserAddress
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.remote.AddressApiInterface
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.BasketApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject


class AddressRepository @Inject constructor(
    private val address: AddressApiInterface,
) : BaseApiResponse() {

    suspend fun getUserAddressList(token: String): NetworkResult<List<UserAddress>> =
        safeApiCall {
            address.getUserAddressList(token)
        }

}
