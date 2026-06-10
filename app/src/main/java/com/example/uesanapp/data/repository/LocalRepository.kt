package com.example.uesanapp.data.repository

import com.example.uesanapp.data.local.CountryDao
import com.example.uesanapp.data.local.CountryEntity
import com.example.uesanapp.data.local.UserDao
import com.example.uesanapp.data.local.UserEntity
import kotlinx.coroutines.flow.Flow

class LocalRepository(
    private val countryDao: CountryDao,
    private val userDao: UserDao
) {
    fun getCountries(): Flow<List<CountryEntity>> {
        return countryDao.getCountries()
    }

    fun getFavoriteCountries(): Flow<List<CountryEntity>> {
        return countryDao.getFavoriteCountries()
    }

    suspend fun initializeCountries(countries: List<CountryEntity>) {
        if (countryDao.countCountries() == 0) {
            countryDao.insertCountries(countries)
        }
    }

    suspend fun updateFavorite(name: String, isFavorite: Boolean) {
        countryDao.updateFavorite(name, isFavorite)
    }

    suspend fun registerUser(name: String, email: String, password: String) {
        userDao.insertUser(
            UserEntity(
                name = name,
                email = email,
                password = password
            )
        )
    }

    suspend fun login(email: String, password: String): Boolean {
        return userDao.login(email, password) != null
    }
}