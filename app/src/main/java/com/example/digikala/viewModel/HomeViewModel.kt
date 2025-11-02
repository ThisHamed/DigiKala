package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.model.home.Category
import com.example.digikala.data.model.home.Slider
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val proposalBanners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val centerBanners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val amazingItems = MutableStateFlow<NetworkResult<List<AmazingItems>>>(NetworkResult.Loading())
    val superMarketItems = MutableStateFlow<NetworkResult<List<AmazingItems>>>(NetworkResult.Loading())
    val categories = MutableStateFlow<NetworkResult<List<Category>>>(NetworkResult.Loading())
    val bestSellProduct = MutableStateFlow<NetworkResult<List<AmazingItems>>>(NetworkResult.Loading())
    val mostVisitedProduct = MutableStateFlow<NetworkResult<List<AmazingItems>>>(NetworkResult.Loading())
    val mostFavoriteProduct = MutableStateFlow<NetworkResult<List<AmazingItems>>>(NetworkResult.Loading())
    val mostDiscountProduct = MutableStateFlow<NetworkResult<List<AmazingItems>>>(NetworkResult.Loading())

    suspend fun getAllDataFromServer() {
        viewModelScope.launch {

            //fire and forget
            launch {
                slider.emit(repository.getSlider())
            }

            launch {
                amazingItems.emit(repository.getAmazingItems())
            }

            launch {
                superMarketItems.emit(repository.getSuperMarketAmazingProducts())
            }

            launch {
                proposalBanners.emit(repository.getProposalBanners())
            }

            launch {
                categories.emit(repository.getCategories())
            }

            launch {
                centerBanners.emit(repository.getCenterBanners())
            }

            launch {
                bestSellProduct.emit(repository.getBestsellerProducts())
            }

            launch {
                mostVisitedProduct.emit(repository.getMostVisitedProducts())
            }

            launch {
                mostFavoriteProduct.emit(repository.getMostFavoriteProducts())
            }

            launch {
                mostDiscountProduct.emit(repository.getMostDiscountedProducts())
            }

        }
    }

}