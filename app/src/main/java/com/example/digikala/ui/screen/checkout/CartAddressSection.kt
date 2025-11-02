package com.example.digikala.ui.screen.checkout

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.address.UserAddress
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.LightCyan
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.AddressViewModel

@Composable
fun CartAddressSection(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel(),
    onAddressReady: (List<UserAddress>) -> Unit
) {

    var addressList by remember {
        mutableStateOf<List<UserAddress>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var address = stringResource(id = R.string.no_address)
    var addressName = ""
    var addressBtnText = stringResource(id = R.string.add_address)

    val addressResult by viewModel.userAddressList.collectAsState()

    when (addressResult) {
        is NetworkResult.Success -> {
            addressList = addressResult.data ?: emptyList()
            if (addressList.isNotEmpty()) {
                onAddressReady(addressList)
                address = addressList[0].address
                addressBtnText = stringResource(id = R.string.change_address)
                address = addressList[0].name
            }
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("1212", "Cart Address Section has a issue: ${addressResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading) {
        val config = LocalConfiguration.current
        Loading(height = config.screenHeightDp.dp, isDark = true)
    } else {
        Row(modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(
                    id = R.drawable.location
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .weight(0.15f)
                    .align(CenterVertically)
            )

            Column(
                modifier = Modifier
                    .weight(0.85f)
                    .padding(MaterialTheme.spacing.medium),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(id = R.string.send_to),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )

                Text(
                    text = address,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3
                )

                Text(
                    text = addressName,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )

            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    .clickable { },
                text = addressBtnText,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.extraSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.LightCyan
            )

            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "",
                tint = MaterialTheme.colors.LightCyan,
                modifier = Modifier
                    .size(12.dp)
                    .align(CenterVertically)
            )

        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium)
                .height(MaterialTheme.spacing.small)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray,
        )
    }

}