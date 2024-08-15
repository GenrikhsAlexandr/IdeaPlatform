package com.aleksandrgenrikhs.ideaplatformtesttask.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val jsonArray = Json.parseToJsonElement(value).jsonArray
        return jsonArray.map { it.jsonPrimitive.content }
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        val jsonArray = JsonArray(list.map { JsonPrimitive(it) })
        return Json.encodeToString(JsonArray.serializer(), jsonArray)
    }
}