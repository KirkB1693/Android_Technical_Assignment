package com.example.androidtechnicalassignment.utils

import android.widget.ImageView
import com.example.androidtechnicalassignment.R
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*


fun getFormattedMileage(mileage: Int?): String {
    val formattedMileage = StringBuilder()
    return if (mileage != null) {
        val kMiles = mileage / 1000.0
        formattedMileage.append("%.1f".format(kMiles))
            .append("k mi")
        formattedMileage.toString()
    } else "Mileage not available"
}

fun getFormattedPrice(price: Int?): String {
    val formattedPrice = StringBuilder()
    return if (price != null) {
        val priceWithCommas = NumberFormat.getNumberInstance(Locale.US).format(price)
        formattedPrice.append("$ %s".format(priceWithCommas))
        formattedPrice.toString()
    } else "Price not available"
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") {
    it.replaceFirstChar { char -> char.titlecase() }
}

fun ImageView.loadImage(uri: String?) {
    Picasso.get()
        .load(uri)
        .fit()
        .centerCrop()
        .placeholder(R.drawable.no_image_icon)
        .error(R.drawable.no_photo_icon_error)
        .into(this)
}



