package com.example.digikala.ui.screen.product_details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.data.model.product_detail.Comment
import com.example.digikala.data.model.product_detail.Price
import com.example.digikala.data.model.product_detail.ProductColor
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.model.product_detail.SliderImage
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.viewModel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    id: String,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    var loading by remember {
        mutableStateOf(false)
    }

    var imageSlider by remember {
        mutableStateOf<List<SliderImage>>(emptyList())
    }
    var productColor by remember {
        mutableStateOf<List<ProductColor>>(emptyList())
    }
    var productComments by remember {
        mutableStateOf<List<Comment>>(emptyList())
    }
    var productPriceList by remember {
        mutableStateOf<List<Price>>(emptyList())
    }

    var productDetailList by remember {
        mutableStateOf(ProductDetail())
    }

    var categoryId by remember {
        mutableStateOf("")
    }
    var description by rememberSaveable {
        mutableStateOf("")
    }

    var technicalFeatures by remember {
        mutableStateOf("")
    }
    var commentCount by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(true) {
        viewModel.getProductById(id)
        viewModel.productDetail.collectLatest { productDetailResult ->
            when (productDetailResult) {
                is NetworkResult.Success -> {
                    productDetailList = productDetailResult.data!!
                    productColor = productDetailResult.data.colors ?: emptyList()
                    productComments = productDetailResult.data.comments ?: emptyList()
                    productPriceList = productDetailResult.data.priceList ?: emptyList()
                    categoryId = productDetailResult.data.categoryId ?: ""
                    commentCount = productDetailResult.data.commentCount ?: 0
                    description = productDetailResult.data.description ?: ""
                    imageSlider = viewModel.productDetail.value.data?.imageSlider ?: emptyList()
                    technicalFeatures = productDetailResult.data.technicalFeatures.toString()
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e(
                        "1212",
                        "product detail Section has a issue: ${productDetailResult.message}"
                    )
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }

    val config = LocalConfiguration.current
    if (loading) {
        Loading(config.screenHeightDp.dp, isDark = true)
    } else {

        Scaffold(
            bottomBar = {
                ProductDetailBottomBar(item = productDetailList, navController)
            },
            topBar = {
                ProductDetailTopBar(navController = navController, priceList = productDetailList)
            }
        ) {
            LazyColumn(modifier = Modifier.padding(bottom = 70.dp)) {
                item { TopSliderProduct(imageSlider) }
                item { ProductDetailHeader(productDetailList) }
                item { ProductSelectColor(productColor) }
                item { SellerInfo(productPrice = productDetailList.price ?: 0) }
                item { SimilarProductSection(categoryId, navController) }
                item { ProductDescription(description, navController, technicalFeatures) }
                item { ProductComment(productComments, commentCount, id, navController) }
                item { ProductSetComment(navController, productDetailList) }
            }
        }
    }
}

