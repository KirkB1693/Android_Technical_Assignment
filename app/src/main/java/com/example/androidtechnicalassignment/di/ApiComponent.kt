package com.example.androidtechnicalassignment.di

import com.example.androidtechnicalassignment.model.CarsService
import com.example.androidtechnicalassignment.viewModel.CarListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CarsService)

    fun inject(viewModel: CarListViewModel)
}