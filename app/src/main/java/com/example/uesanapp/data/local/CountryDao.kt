package com.example.uesanapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM countries ORDER BY ranking ASC")
    fun getCountries(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM countries WHERE isFavorite = 1 ORDER BY ranking ASC")
    fun getFavoriteCountries(): Flow<List<CountryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryEntity>): List<Long>

    @Query("UPDATE countries SET isFavorite = :isFavorite WHERE name = :name")
    suspend fun updateFavorite(name: String, isFavorite: Boolean): Int

    @Query("SELECT COUNT(*) FROM countries")
    suspend fun countCountries(): Int
}