package com.example.data.database.converter

import androidx.room.TypeConverter
import com.example.domain.model.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocationConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Location>() {}.type

    @TypeConverter
    fun stringToLocation(json: String): Location? {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun locationToString(translations: Location?): String {
        return gson.toJson(translations, type)
    }
}