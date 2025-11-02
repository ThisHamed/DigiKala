package com.example.digikala.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.viewModel.HomeViewModel

@Composable
fun AmazingOfferSection(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var amazingItemList by remember {
        mutableStateOf<List<AmazingItems>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val amazingItemResult by viewModel.amazingItems.collectAsState()

    when (amazingItemResult) {
        is NetworkResult.Success -> {
            amazingItemList = amazingItemResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("1212", "Amazing Item Section has a issue: ${amazingItemResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading) {
        val config = LocalConfiguration.current
        Loading(height = config.screenHeightDp.dp, isDark = true)
    } else {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.DigikalaLightRed)
                .fillMaxWidth()
        ) {

            LazyRow(modifier = Modifier.background(MaterialTheme.colors.DigikalaLightRed)) {

                item {
                    AmazingOfferCard(
                        com.example.digikala.R.drawable.amazings,
                        com.example.digikala.R.drawable.box
                    )
                }

                items(items = amazingItemList) {
                    AmazingItem(item = it, navController = navController)
                }

                item {
                    AmazingShowMoreItem()
                }
            }

        }
    }

}