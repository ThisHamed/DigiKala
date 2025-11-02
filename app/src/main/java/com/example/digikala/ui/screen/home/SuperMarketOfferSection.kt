package com.example.digikala.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.DigikalaLightGreen
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.viewModel.HomeViewModel

@Composable
fun SuperMarketOfferSection(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var superMarketItemList by remember {
        mutableStateOf<List<AmazingItems>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val superMarketItemResult by viewModel.superMarketItems.collectAsState()

    when (superMarketItemResult) {
        is NetworkResult.Success -> {
            superMarketItemList = superMarketItemResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("1212", "SuperMarket Offer Section has a issue: ${superMarketItemResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.DigikalaLightRed)
            .fillMaxWidth()
    ) {

        LazyRow(modifier = Modifier.background(MaterialTheme.colors.DigikalaLightGreen)) {

            item {
                SuperMarketOfferCard(
                    R.drawable.supermarketamazings,
                    R.drawable.fresh
                )
            }

            items(superMarketItemList) {

                AmazingItem(item = it, navController = navController)
            }

            item {
                AmazingShowMoreItem()
            }
        }

    }

}