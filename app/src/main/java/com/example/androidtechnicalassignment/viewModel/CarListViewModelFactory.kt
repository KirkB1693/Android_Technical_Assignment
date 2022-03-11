package com.example.androidtechnicalassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtechnicalassignment.database.CarDao

class CarListViewModelFactory(private val datasource: CarDao) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CarListViewModel::class.java)) {
                return CarListViewModel(datasource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}