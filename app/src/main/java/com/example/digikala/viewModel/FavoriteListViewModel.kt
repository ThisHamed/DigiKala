package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.model.profile.FavoriteItem
import com.example.digikala.repository.FavoriteListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val repo: FavoriteListRepository
) : ViewModel() {

    val allFavoriteItem: Flow<List<FavoriteItem>> = repo.allFavoriteItems

    fun addFavoriteItem(favoriteItem: FavoriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addFavItem(favoriteItem)
        }
    }

    fun deleteFavoriteItem(favoriteItem: FavoriteItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteFavoriteItem(favoriteItem)
        }
    }

    fun clearFavList() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.clearFavList()
        }
    }

    fun isItemExist(id: String): Flow<Boolean> = repo.isItemExist(id)

}