package com.example.androidtechnicalassignment.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CarfaxJsonResponse(
    @SerializedName("listings")
    var listings : List<Car>?
)

@Entity(tableName = "car_table")
@Parcelize
data class Car(
    @SerializedName("id")
    @PrimaryKey val id : String,
    @SerializedName("dealer")
    @ColumnInfo(name = "dealer") val dealer: Dealer?,
    @SerializedName("vin")
    @ColumnInfo(name = "vin") val vin : String,
    @SerializedName("images")
    @ColumnInfo(name = "images") val images : Images?,
    @SerializedName("year")
    @ColumnInfo(name = "year") val year : String?,
    @SerializedName("make")
    @ColumnInfo(name = "make") val make : String?,
    @SerializedName("model")
    @ColumnInfo(name = "model") val model : String?,
    @SerializedName("trim")
    @ColumnInfo(name = "trim") val trim : String?,
    @SerializedName("currentPrice")
    @ColumnInfo(name = "price") val price : Int?,
    @SerializedName("mileage")
    @ColumnInfo(name = "mileage") val mileage : Int?,
    @SerializedName("interiorColor")
    @ColumnInfo(name = "interiorColor") val interiorColor : String?,
    @SerializedName("exteriorColor")
    @ColumnInfo(name = "exteriorColor") val exteriorColor : String?,
    @SerializedName("drivetype")
    @ColumnInfo(name = "driveType") val driveType : String?,
    @SerializedName("transmission")
    @ColumnInfo(name = "transmission") val transmission : String?,
    @SerializedName("engine")
    @ColumnInfo(name = "engine") val engine : String?,
    @SerializedName("bodytype")
    @ColumnInfo(name = "bodyStyle") val bodyStyle : String?,
    @SerializedName("fuel")
    @ColumnInfo(name = "fuelType") val fuelType : String?
) : Parcelable

@Parcelize
data class Dealer(
    @SerializedName("phone")
    val dealerPhone : String?,
    @SerializedName("city")
    val city : String?,
    @SerializedName("state")
    val state : String?
) : Parcelable

@Parcelize
data class Images(
    @SerializedName("firstPhoto")
    val firstPhoto : FirstPhoto?
) : Parcelable

@Parcelize
data class FirstPhoto(
    @SerializedName("large")
    val large : String?
) : Parcelable