package com.example.digikala.ui.screen.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.utils.Constants.USER_PHONE
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.utils.InputValidation.isValidPassword
import com.example.digikala.viewModel.DatastoreViewModel
import com.example.digikala.viewModel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DatastoreViewModel = hiltViewModel()

) {

    val context = LocalContext.current



    LaunchedEffect(Dispatchers.Main) {
        profileViewModel.loginResponse.collectLatest { loginResponse ->
            when (loginResponse) {
                is NetworkResult.Success -> {
                    loginResponse.data?.let { user ->
                        if (user.token.isNotEmpty()) {
                            dataStore.saveUserToken(user.token)
                            dataStore.saveUserId(user.id)
                            dataStore.saveUserPhone(user.phone)
                            dataStore.saveUserName(user.name ?: "null")
                            USER_NAME = user.name ?: "null"
                            USER_PHONE = user.phone
                            USER_TOKEN = user.token
                            dataStore.saveUserPassword(profileViewModel.inputPasswordState)

                            profileViewModel.screenState = ProfileScreenState.PROFILE_STATE
                        }
                    }
                    Toast.makeText(
                        context,
                        loginResponse.message,
                        Toast.LENGTH_LONG
                    ).show()
                    profileViewModel.loadingState = false
                }
                is NetworkResult.Error -> {
                    profileViewModel.loadingState = false
                    Log.e("1212", "loginResponse error : ${loginResponse.message}")
                }
                is NetworkResult.Loading -> {}
            }
        }

    }




    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.digi_settings
                    ), contentDescription = "",
                    modifier = Modifier
                        .padding(
                            MaterialTheme.spacing.small
                        )
                        .size(MaterialTheme.spacing.semiLarge),
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small),
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        Text(
            text = stringResource(id = R.string.set_password_text),
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.semiLarge
            ),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold
        )

        MyEditText(
            value = profileViewModel.inputPhoneState,
            placeHolder = stringResource(id = R.string.phone_and_email),
            onValueChanged = {},
        )


        MyEditText(
            value = profileViewModel.inputPasswordState,
            placeHolder = stringResource(id = R.string.set_password),
            onValueChanged = {
                profileViewModel.inputPasswordState = it
            }
        )


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        if (profileViewModel.loadingState) {
            LoadingButton()
        } else {
            MyButton(text = stringResource(id = R.string.digikala_login)) {
                if (isValidPassword(profileViewModel.inputPasswordState)) {
                    profileViewModel.login()
                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.password_format_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


    }


}