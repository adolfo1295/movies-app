package com.adolfo.android.moviesapp.data.local.cart

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adolfo.android.moviesapp.data.local.cart.dao.MovieCartDao
import com.adolfo.android.moviesapp.data.local.cart.entity.MovieCartEntity

@Database(
    entities = [MovieCartEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieCartDatabase : RoomDatabase() {
    abstract fun movieCartDao(): MovieCartDao
}