package com.example.digikala.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.utils.Constants
import com.example.digikala.utils.LocaleUtils
import com.example.digikala.viewModel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController)
}

@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    LocaleUtils.setLocale(LocalContext.current, Constants.USER_LANGUAGE)

    LaunchedEffect(true) {
        refreshDataFromServer(viewModel)
    }

    SwipeRefreshSection(viewModel = viewModel, navController = navController)

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SwipeRefreshSection(viewModel: HomeViewModel, navController: NavHostController) {
    val refreshScope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(refreshing = false, onRefresh = {
        refreshScope.launch {
            refreshDataFromServer(viewModel)
            Log.e("1212", "swipe Refresh")
        }
    })
    Box(
        Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {

            item { SearchBarSection() }
            item { TopSliderSection() }
            item { ShowCaseSection(navController) }
            item { AmazingOfferSection(navController) }
            item { ProposalCardSection() }
            item { SuperMarketOfferSection(navController) }
            item { CategoryListSection() }
            item { CenterBannerSection(1) }
            item { BestSellerOfferSection() }
            item { CenterBannerSection(2) }
            item { MostFavoriteProductSection(navController = navController) }
            item { CenterBannerSection(3) }
            item { MostVisitedProductSection() }
            item { CenterBannerSection(4) }
            item { CenterBannerSection(5) }
            item { MostDiscountProductSection() }


        }




        PullRefreshIndicator(
            refreshing = false,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

private suspend fun refreshDataFromServer(viewModel: HomeViewModel) {
    viewModel.getAllDataFromServer()
}