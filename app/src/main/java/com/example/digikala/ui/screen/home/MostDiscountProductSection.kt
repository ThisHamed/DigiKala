package com.example.digikala.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.digikala.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MostDiscountProductSection(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var mostDiscountList by remember {
        mutableStateOf<List<AmazingItems>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val mostDiscountResult by viewModel.mostDiscountProduct.collectAsState()

    when (mostDiscountResult) {
        is NetworkResult.Success -> {
            mostDiscountList = mostDiscountResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("1212", "Most Discount Offer Section has a issue: ${mostDiscountResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            text = stringResource(id = R.string.most_discounted_products),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText
        )

        FlowRow(
            maxItemsInEachRow = 2,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start
        ) {

            for (item in mostDiscountList) {

                MostDiscountCard(item)
            }

        }

    }

}