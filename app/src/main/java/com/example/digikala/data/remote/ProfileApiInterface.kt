package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.model.profile.UserNameRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApiInterface {

    @POST("v1/login")
    suspend fun login(
        @Body login: LoginRequest
    ): Response<ResponseResult<LoginResponse>>

    @POST("v1/setUserName")
    suspend fun userName(
        @Body userNameRequest: UserNameRequest
    ): Response<ResponseResult<String>>

}