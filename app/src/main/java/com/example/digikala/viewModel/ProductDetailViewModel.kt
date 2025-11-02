package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.digikala.data.model.home.AmazingItems
import com.example.digikala.data.model.product_detail.Comment
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.data.source.ProductCommentsDataSource
import com.example.digikala.repository.ProductDetailRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repository: ProductDetailRepository) :
    ViewModel() {

    val productDetail =
        MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())

    val similarProduct =
        MutableStateFlow<NetworkResult<List<AmazingItems>>>(NetworkResult.Loading())

    val newCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())


    fun getProductById(id: String) {
        viewModelScope.launch {
            productDetail.emit(repository.getProductById(id))
        }
    }

    fun getSimilarProducts(categoryId: String) {
        viewModelScope.launch {
            similarProduct.emit(repository.getSimilarProducts(categoryId))
        }
    }

    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repository.setNewComment(newComment))
        }
    }

    var commentList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }
    fun getCommentList(productId: String) {
        commentList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            ProductCommentsDataSource(repository, productId)
        }.flow.cachedIn(viewModelScope)
    }


}