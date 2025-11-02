package com.example.digikala.ui.screen.basket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.screen.profile.Profile
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.BasketViewModel

@Composable
fun ShoppingCart(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    var isCartEmpty by remember {
        mutableStateOf(true)
    }

    val cartDetail = viewModel.cartDetails.collectAsState()

    val currentCartItemsState: BasketScreenState<List<CartItem>> by
    viewModel.currentCartItem.collectAsState(BasketScreenState.Loading)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 60.dp)
        ) {

            item {
                if (USER_TOKEN == "null") {
                    LoginOrRegisterSection(navController)
                }
            }

            when (currentCartItemsState) {

                is BasketScreenState.Success -> {

                    if ((currentCartItemsState as BasketScreenState.Success<List<CartItem>>).data.isEmpty()) {
                        isCartEmpty = true
                        item { EmptyBasketShopping() }
                        item { SuggestListSection() }
                    } else {
                        isCartEmpty = false
                        items((currentCartItemsState as BasketScreenState.Success<List<CartItem>>).data) { item ->
                            CartItemCard(cart = item, mode = CartStatus.CURRENT_CART)
                        }

                        item { CartPriceDetailSection(cartDetail.value) }

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
        if (!isCartEmpty) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 60.dp)
            ) {
                BuyProcessContinue(cartDetail.value.payablePrice) {
                    if (USER_TOKEN == "null") {
                        navController.navigate(Screen.Profile.route)
                    } else {
                        navController.navigate(Screen.Checkout.route)
                    }
                }
            }
        }
    }
}