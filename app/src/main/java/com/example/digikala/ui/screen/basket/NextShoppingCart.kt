package com.example.digikala.ui.screen.basket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants
import com.example.digikala.viewModel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NextShoppingCart(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    val nextCartItemsState: BasketScreenState<List<CartItem>> by
    viewModel.nextCartItems.collectAsState(BasketScreenState.Loading)


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 60.dp)
    ) {

        item {
            if (Constants.USER_TOKEN == "null") {
                LoginOrRegisterSection(navController)
            }
        }

        when (nextCartItemsState) {

            is BasketScreenState.Success -> {

                if ((nextCartItemsState as BasketScreenState.Success<List<CartItem>>).data.isEmpty()) {
                    item { EmptyNextShoppingList() }
                } else {
                    items((nextCartItemsState as BasketScreenState.Success<List<CartItem>>).data) { item ->
                        CartItemCard(cart = item, mode = CartStatus.NEXT_CART)
                    }
                }

            }

            is BasketScreenState.Error -> {
                item {
                    Log.e("1212", "Error")
                }
            }

            is BasketScreenState.Loading -> {
                item {

                    Column(
                        modifier = Modifier
                            .height(LocalConfiguration.current.screenHeightDp.dp - 60.dp)
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.spacing.small),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = stringResource(id = R.string.please_wait),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.darkText
                        )

                    }

                }
            }

        }

    }

}