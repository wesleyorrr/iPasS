package com.freelanceror.ipaas.data

import android.content.Context
import com.freelanceror.ipaas.model.NasaPicture
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("NasaAppPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun savePictures(pictures: List<NasaPicture>) {
        val json = gson.toJson(pictures)
        sharedPreferences.edit().putString("pictures", json).apply()
    }

    fun getSavedPictures(): List<NasaPicture> {
        val json = sharedPreferences.getString("pictures", null) ?: return emptyList()
        val type = object : TypeToken<List<NasaPicture>>() {}.type
        return gson.fromJson(json, type)
    }
}