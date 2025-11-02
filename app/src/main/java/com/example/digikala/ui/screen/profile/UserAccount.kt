package com.example.digikala.ui.screen.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.profile.UserNameRequest
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading3Dots
import com.example.digikala.ui.theme.CursorColor
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.DatastoreViewModel
import com.example.digikala.viewModel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UserAccountScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DatastoreViewModel = hiltViewModel()
) {
    var name = ""
    var family = ""
    if (USER_NAME != "null") {
        val nameParts = USER_NAME.split(" - ")
        name = nameParts[0]
        family = nameParts[1]
    }

    var firstName by remember {
        mutableStateOf(name)
    }
    var lastName by remember {
        mutableStateOf(family)
    }

    LaunchedEffect(true) {
        viewModel.userNameResponse.collectLatest {
            when (it) {
                is NetworkResult.Success -> {
                    USER_NAME = "$firstName - $lastName"
                    dataStore.saveUserName("$firstName - $lastName")
                    navController.popBackStack()
                }

                is NetworkResult.Error -> {
                    viewModel.loadingState = false
                    Log.e("1212", it.message.toString())
                }

                is NetworkResult.Loading -> {}
            }
        }
    }

    Column {
        UserAccountHeader(navController = navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Text(
                text = stringResource(id = R.string.enter_name_lastname),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Text(
                text = stringResource(id = R.string.firstname),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                shape = MaterialTheme.roundedShape.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.searchBarBg,
                    focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                    unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
                    cursorColor = MaterialTheme.colors.CursorColor,
                ),
                keyboardActions = KeyboardActions.Default,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = stringResource(id = R.string.lastname),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                shape = MaterialTheme.roundedShape.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.searchBarBg,
                    focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                    unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
                    cursorColor = MaterialTheme.colors.CursorColor,
                ),
                keyboardActions = KeyboardActions.Default,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(MaterialTheme.spacing.small),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                if (viewModel.loadingState) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = MaterialTheme.roundedShape.small,
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                    ) {
                        Loading3Dots(isDark = true)
                    }
                } else {
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            if (firstName.isNotBlank() && lastName.isNotBlank()) {
                                viewModel.userName(
                                    UserNameRequest(
                                        token = USER_TOKEN,
                                        name = "$firstName - $lastName"
                                    )
                                )
                            } else {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.userToast),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = MaterialTheme.roundedShape.small,
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                    ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.confirm_information),
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                }

            }

        }


    }
}

@Composable
private fun UserAccountHeader(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "",
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }
            Text(
                text = stringResource(id = R.string.user_information),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.extraSmall)
                .background(MaterialTheme.colors.searchBarBg)
                .fillMaxWidth()
        )

    }
}