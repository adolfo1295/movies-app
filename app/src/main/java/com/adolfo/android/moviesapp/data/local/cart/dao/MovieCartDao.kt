package com.adolfo.android.moviesapp.data.local.cart.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adolfo.android.moviesapp.data.local.cart.entity.MovieCartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovieToCart(movieCartEntity: MovieCartEntity)

    @Query("select * from movieCartEntity")
    fun getMovieCart(): Flow<List<MovieCartEntity>>

}