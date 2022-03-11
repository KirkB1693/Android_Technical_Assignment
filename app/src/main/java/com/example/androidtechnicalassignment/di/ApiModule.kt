package com.example.androidtechnicalassignment.di

import com.example.androidtechnicalassignment.model.CarsAPI
import com.example.androidtechnicalassignment.model.CarsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://carfax-for-consumers.firebaseio.com/"

    @Provides
    fun provideCarsApi(): CarsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(
                CarsAPI::
                class.java
            )
    }

    @Provides
    fun provideCarsService(): CarsService {
        return CarsService()
    }

}