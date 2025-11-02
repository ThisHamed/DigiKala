package com.example.digikala.ui.screen.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.bottomBar
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BasketScreen(navController: NavHostController) {
    Basket(navController)
}

@Composable
fun Basket(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val currentCartItemCount = viewModel.currentCartItemsCount.collectAsState(0)
    val nextCartItemCount = viewModel.nextCartItemsCount.collectAsState(0)

    val tabTitles = listOf(
        stringResource(id = R.string.cart),
        stringResource(id = R.string.next_cart_list)
    )

    Column {

        TabRow(
            modifier = Modifier.padding(MaterialTheme.spacing.medium),
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colors.digikalaRed,
            backgroundColor = MaterialTheme.colors.bottomBar,
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectedTabIndex])
                        .height(3.dp)
                        .background(Color.Red)
                )
            }
        ) {

            tabTitles.forEachIndexed { index, title ->

                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    selectedContentColor = MaterialTheme.colors.digikalaRed,
                    unselectedContentColor = Color.Gray,
                    text = {
                        Row {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.SemiBold
                            )

                            val cartCounter = if (index == 0) {
                                currentCartItemCount
                            } else {
                                nextCartItemCount
                            }

                            if (cartCounter.value > 0) {
                                Spacer(modifier = Modifier.width(10.dp))
                                SetBadgeToTab(
                                    selectedTabIndex = selectedTabIndex,
                                    index = index,
                                    cartCounter = cartCounter.value
                                )
                            }

                        }
                    }
                )

            }

        }

        when (selectedTabIndex) {
            0 -> ShoppingCart(navController)
            1 -> NextShoppingCart(navController)
        }

    }


}