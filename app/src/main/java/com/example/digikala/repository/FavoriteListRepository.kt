package com.example.digikala.repository

import com.example.digikala.data.db.FavoriteListDao
import com.example.digikala.data.model.profile.FavoriteItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavoriteListRepository @Inject constructor(
    private val dao: FavoriteListDao
) {
    val allFavoriteItems: Flow<List<FavoriteItem>> = dao.getAllFavItems()

    suspend fun deleteFavoriteItem(favoriteItem: FavoriteItem) {
        dao.deleteItem(favoriteItem)
    }

    suspend fun clearFavList() {
        dao.clearFavList()
    }

    suspend fun addFavItem(favoriteItem: FavoriteItem) {
        dao.addFavItem(favoriteItem)
    }

    fun isItemExist(id: String): Flow<Boolean> = dao.isItemExist(id)

}
