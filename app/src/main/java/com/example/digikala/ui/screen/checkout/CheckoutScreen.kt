package com.example.digikala.ui.screen.checkout

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.basket.CartDetails
import com.example.digikala.data.model.checkout.OrderDetails
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.screen.basket.BuyProcessContinue
import com.example.digikala.ui.screen.basket.CartPriceDetailSection
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.BasketViewModel
import com.example.digikala.viewModel.CheckoutViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {
    val cartDetail by basketViewModel.cartDetails.collectAsState()
    val currentCartItems by basketViewModel.ourCartItem.collectAsState()

    var address by remember { mutableStateOf("") }
    var addressName by remember { mutableStateOf("") }
    var addressPhone by remember { mutableStateOf("") }

    var shippingCost by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }


    LaunchedEffect(true) {
        if (address.isNotBlank())
            checkoutViewModel.getShippingCost(address)
        else
            checkoutViewModel.getShippingCost("")
    }


    val shippingCostResult by checkoutViewModel.shippingCost.collectAsState()
    when (shippingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shippingCostResult.data ?: 0
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("1212", "CheckoutScreen error : ${shippingCostResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    var orderId by remember { mutableStateOf("") }

    LaunchedEffect(Dispatchers.Main) {
        checkoutViewModel.orderResponse.collectLatest { orderResult ->
            when (orderResult) {
                is NetworkResult.Success -> {
                    orderId = orderResult.data ?: ""
                    navController.navigate(
                        Screen.ConfirmPurchase.withArgs(
                            orderId,
                            cartDetail.payablePrice + shippingCost
                        )
                    )
                    Log.e("1212", orderId)
                    // loading = false
                }

                is NetworkResult.Error -> {
                    //loading = false
                    Log.e("1212", "CheckoutScreen error : ${orderResult.message}")
                }

                is NetworkResult.Loading -> {
                    // loading = true
                }
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            DeliveryTimeBottomSheet()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn() {
                item { CheckoutTopBarSection(navController) }
                item {
                    CartAddressSection(navController) { addressList ->
                        if (addressList.isNotEmpty()) {
                            address = addressList[0].address
                            addressName = addressList[0].name
                            addressPhone = addressList[0].phone
                        }
                    }
                }
                item {
                    CartItemReviewSection(cartDetail, currentCartItems) {
                        coroutineScope.launch {
                            if (modalSheetState.isVisible) {
                                modalSheetState.hide()
                            } else {
                                modalSheetState.show()
                            }
                        }

                    }
                }
                item { CartInfoSection() }
                item { CartPriceDetailSection(cartDetail, shippingCost) }
            }


            if (loading) {
                Loading(height = 65.dp, isDark = true)
            } else {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    BuyProcessContinue(cartDetail.payablePrice, shippingCost) {
                        checkoutViewModel.addNewOrder(
                            OrderDetails(
                                orderAddress = address,
                                orderProducts = currentCartItems,
                                orderTotalDiscount = cartDetail.totalDiscount,
                                orderTotalPrice = cartDetail.payablePrice + shippingCost,
                                orderUserName = addressName,
                                orderUserPhone = addressPhone,
                                token = USER_TOKEN
                            )
                        )
                    }
                }
            }

        }
    }


}


