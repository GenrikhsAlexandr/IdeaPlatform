package com.aleksandrgenrikhs.ideaplatformtesttask.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsCatalogDao {

    @Query("SELECT * FROM item")
    fun getAllProducts(): Flow<List<ProductEntry>>

    @Query("UPDATE item SET amount = :newAmount WHERE id = :id")
    suspend fun updateProductAmount(id: Int, newAmount: Int)

    @Query("DELETE FROM item WHERE id = :id")
    suspend fun deleteProductById(id: Int)
}
