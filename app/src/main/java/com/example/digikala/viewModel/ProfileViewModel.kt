package com.example.digikala.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.model.home.Category
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.model.profile.LoginRequest
import com.example.digikala.data.model.profile.LoginResponse
import com.example.digikala.data.model.profile.UserNameRequest
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.HomeRepository
import com.example.digikala.repository.ProfileRepository
import com.example.digikala.ui.screen.profile.ProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {
    //sharedViewModel
    var screenState by mutableStateOf(ProfileScreenState.LOGIN_STATE)

    var inputPhoneState by mutableStateOf("")
    var inputPasswordState by mutableStateOf("")
    var loadingState by mutableStateOf(false)

    val loginResponse = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())
    val userNameResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun login() {
        viewModelScope.launch {
            loadingState = true
            val loginRequest = LoginRequest(inputPhoneState, inputPasswordState)
            loginResponse.emit(repository.login(loginRequest))
        }
    }

    fun userName(userNameRequest: UserNameRequest) {
        loadingState = true
        viewModelScope.launch {
            userNameResponse.emit(repository.userName(userNameRequest))
        }
    }


    fun refreshToken(phone: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(phone, password)
            loginResponse.emit(repository.login(loginRequest))
        }
    }

}