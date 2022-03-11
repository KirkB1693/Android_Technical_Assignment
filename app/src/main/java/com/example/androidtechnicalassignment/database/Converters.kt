package com.example.androidtechnicalassignment.database

import androidx.room.TypeConverter
import com.example.androidtechnicalassignment.model.Dealer
import com.example.androidtechnicalassignment.model.Images
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromDealerToString(dealer: Dealer?): String? {
        if (dealer == null) return null
        val gson = Gson()
        val type: Type =
            object : TypeToken<Dealer?>() {}.type
        return gson.toJson(dealer, type)
    }

    @TypeConverter
    fun stringToDealer(dealerString: String?): Dealer? {
        if (dealerString.isNullOrEmpty()) return null
        val myType: Type = object : TypeToken<Dealer>() {}.type
        return Gson().fromJson<Dealer?>(dealerString, myType)
    }

    @TypeConverter
    fun fromImagesToString(images: Images?): String? {
        if (images == null) return null
        val gson = Gson()
        val type: Type =
            object : TypeToken<Images?>() {}.type
        return gson.toJson(images, type)
    }

    @TypeConverter
    fun stringToImages(imagesString: String?): Images? {
        if (imagesString.isNullOrEmpty()) return null
        val myType: Type = object : TypeToken<Images>() {}.type
        return Gson().fromJson<Images?>(imagesString, myType)
    }
}