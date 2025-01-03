package com.freelanceror.ipaas.repository

import com.freelanceror.ipaas.network.RetrofitInstance

class NasaRepository {
    suspend fun getPictures(apiKey: String) = RetrofitInstance.api.getPictures(apiKey)
}