package com.example.androidtechnicalassignment.model

import io.reactivex.Single
import retrofit2.http.GET

interface CarsAPI {
    @GET("assignment.json")
    fun getCars(): Single<CarfaxJsonResponse>
}