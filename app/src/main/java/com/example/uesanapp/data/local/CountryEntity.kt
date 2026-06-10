package com.example.uesanapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey val name: String,
    val ranking: Int,
    val imageUrl: String,
    val isFavorite: Boolean
)