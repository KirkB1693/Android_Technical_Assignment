package com.example.androidtechnicalassignment.model

import com.example.androidtechnicalassignment.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CarsService {
    @Inject
    lateinit var api: CarsAPI

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCars(): Single<CarfaxJsonResponse> {
        return api.getCars()
    }
}