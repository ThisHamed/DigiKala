package com.example.digikala.repository

import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.model.home.Category
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.BaseApiResponse
import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: HomeApiInterface
) : BaseApiResponse() {

    suspend fun getSlider(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }

    suspend fun getAmazingItems(): NetworkResult<List<AmazingItems>> =
        safeApiCall {
            api.getAmazingItems()
        }

    suspend fun getSuperMarketAmazingProducts(): NetworkResult<List<AmazingItems>> =
        safeApiCall {
            api.getSuperMarketAmazingProducts()
        }

    suspend fun getProposalBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getProposalBanners()
        }

    suspend fun getCategories(): NetworkResult<List<Category>> =
        safeApiCall {
            api.getCategories()
        }

    suspend fun getCenterBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getCenterBanners()
        }

    suspend fun getBestsellerProducts(): NetworkResult<List<AmazingItems>> =
        safeApiCall {
            api.getBestsellerProducts()
        }

    suspend fun getMostVisitedProducts(): NetworkResult<List<AmazingItems>> =
        safeApiCall {
            api.getMostVisitedProducts()
        }

    suspend fun getMostFavoriteProducts(): NetworkResult<List<AmazingItems>> =
        safeApiCall {
            api.getMostFavoriteProducts()
        }

    suspend fun getMostDiscountedProducts(): NetworkResult<List<AmazingItems>> =
        safeApiCall {
            api.getMostDiscountedProducts()
        }

}