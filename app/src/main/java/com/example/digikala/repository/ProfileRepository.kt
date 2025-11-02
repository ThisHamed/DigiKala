package com.example.digikala.repository

import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.model.home.Category
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.model.profile.UserNameRequest
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.remote.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: ProfileApiInterface
) : BaseApiResponse() {

    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
            api.login(loginRequest)
        }

    suspend fun userName(userNameRequest: UserNameRequest): NetworkResult<String> =
        safeApiCall {
            api.userName(userNameRequest)
        }

}