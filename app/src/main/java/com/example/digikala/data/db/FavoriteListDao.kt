package com.example.digikala.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digikala.data.model.profile.FavoriteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteListDao {

    @Query("SELECT * FROM favorite_list_table")
    fun getAllFavItems(): Flow<List<FavoriteItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavItem(favoriteItem: FavoriteItem)

    @Delete
    suspend fun deleteItem(favoriteItem: FavoriteItem)

    @Query("DELETE FROM favorite_list_table")
    suspend fun clearFavList()

    @Query("SELECT EXISTS(SELECT * FROM favorite_list_table WHERE id = :id)")
    fun isItemExist(id: String): Flow<Boolean>

}