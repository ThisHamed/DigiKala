package com.example.digikala.ui.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewModel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProposalCardSection(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var bannerList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val bannerResult by viewModel.proposalBanners.collectAsState()

    when (bannerResult) {
        is NetworkResult.Success -> {
            bannerList = bannerResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("1212", "Banners Section has a issue: ${bannerResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading) {
        val config = LocalConfiguration.current
        Loading(height = config.screenHeightDp.dp, isDark = true)
    } else {
        FlowRow(
            maxItemsInEachRow = 2,
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .padding(MaterialTheme.spacing.small)
        ) {

            for (item in bannerList) {
                ProposalCardItem(imgLink = item)
            }

        }
    }
}

@Composable
fun ProposalCardItem(imgLink: Slider) {

    Card(
        shape = MaterialTheme.roundedShape.semiMedium,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(140.dp)
            .padding(
                vertical = MaterialTheme.spacing.small,
                horizontal = MaterialTheme.spacing.small
            )
    ) {

        Image(
            painter = rememberAsyncImagePainter(imgLink.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

}