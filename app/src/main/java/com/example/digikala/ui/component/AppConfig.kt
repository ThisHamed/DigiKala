package com.example.digikala.ui.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.utils.Constants.USER_ID
import com.example.digikala.utils.Constants.USER_LANGUAGE
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.utils.Constants.USER_PASSWORD
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.DatastoreViewModel
import com.example.digikala.viewModel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppConfig(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DatastoreViewModel = hiltViewModel()
) {

    getDataStoreVariables(dataStore)

    profileViewModel.refreshToken(USER_PHONE, USER_PASSWORD)


    LaunchedEffect(Dispatchers.Main) {
        profileViewModel.loginResponse.collectLatest { loginResponse ->
            when (loginResponse) {
                is NetworkResult.Success -> {
                    loginResponse.data?.let { user ->
                        if (user.token.isNotEmpty()) {
                            dataStore.saveUserToken(user.token)
                            dataStore.saveUserId(user.id)
                            dataStore.saveUserPhone(user.phone)
                            dataStore.saveUserPassword(USER_PASSWORD)
                            dataStore.saveUserName(user.name ?: "null")

                            getDataStoreVariables(dataStore)

                            Log.e("1212", "refresh token")
                        }

                    }
                }

                else -> {}
            }
        }

    }

}

private fun getDataStoreVariables(dataStore: DatastoreViewModel) {
    USER_LANGUAGE = dataStore.getUserLanguage()
    dataStore.saveUserLanguage(USER_LANGUAGE)

    USER_PHONE = dataStore.getUserPhone().toString()
    USER_PASSWORD = dataStore.getUserPassword().toString()
    USER_TOKEN = dataStore.getUserToken().toString()
    USER_ID = dataStore.getUserId().toString()
    USER_NAME = dataStore.getUserName().toString()
}