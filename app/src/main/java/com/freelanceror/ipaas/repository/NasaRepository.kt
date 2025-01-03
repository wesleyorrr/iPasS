package com.freelanceror.ipaas.repository

class NasaRepository {
    suspend fun getPictures(apiKey: String) = RetrofitInstance.api.getPictures(apiKey)
}