package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.datastore.DatastoreRepository
import com.example.digikala.utils.Constants.PERSIAN_LANGUAGE
import com.example.digikala.utils.Constants.USER_LANGUAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DatastoreViewModel @Inject constructor(
    private val repository: DatastoreRepository
) : ViewModel() {

    companion object {
        const val USER_LANGUAGE_KEY = "USER_LANGUAGE_KEY"
        const val USER_TOKEN_KEY = "USER_TOKEN_KEY"
        const val USER_ID_KEY = "USER_ID_KEY"
        const val USER_PHONE_KEY = "USER_PHONE_KEY"
        const val USER_PASSWORD_KEY = "USER_PASSWORD_KEY"
        const val USER_NAME_KEY = "USER_NAME_KEY"
    }

    fun saveUserLanguage(value: String) {
        viewModelScope.launch {
            repository.putString(USER_LANGUAGE_KEY, value)
        }
    }

    fun getUserLanguage(): String = runBlocking {
        repository.getString(USER_LANGUAGE_KEY) ?: PERSIAN_LANGUAGE
    }

    fun saveUserToken(value: String) {
        viewModelScope.launch {
            repository.putString(USER_TOKEN_KEY, value)
        }
    }

    fun getUserToken(): String? = runBlocking {
        repository.getString(USER_TOKEN_KEY)
    }

    fun saveUserId(value: String) {
        viewModelScope.launch {
            repository.putString(USER_ID_KEY, value)
        }
    }

    fun getUserId(): String? = runBlocking {
        repository.getString(USER_ID_KEY)
    }

    fun saveUserPhone(value: String) {
        viewModelScope.launch {
            repository.putString(USER_PHONE_KEY, value)
        }
    }

    fun getUserPhone(): String? = runBlocking {
        repository.getString(USER_PHONE_KEY)
    }


    fun saveUserPassword(value: String) {
        viewModelScope.launch {
            repository.putString(USER_PASSWORD_KEY, value)
        }
    }

    fun getUserPassword(): String? = runBlocking {
        repository.getString(USER_PASSWORD_KEY)
    }

    fun saveUserName(value: String) {
        viewModelScope.launch {
            repository.putString(USER_NAME_KEY, value)
        }
    }

    fun getUserName(): String? = runBlocking {
        repository.getString(USER_NAME_KEY)
    }

}
