package com.kimboo.core.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class RoomTypeConverters {

    @TypeConverter
    fun fromStringToListString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listStringToString(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}
