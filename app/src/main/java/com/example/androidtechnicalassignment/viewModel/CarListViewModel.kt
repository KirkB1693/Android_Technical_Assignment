package com.example.androidtechnicalassignment.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtechnicalassignment.database.CarDao
import com.example.androidtechnicalassignment.di.DaggerApiComponent
import com.example.androidtechnicalassignment.model.Car
import com.example.androidtechnicalassignment.model.CarfaxJsonResponse
import com.example.androidtechnicalassignment.model.CarsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarListViewModel(private val datasource: CarDao) : ViewModel(){
    @Inject
    lateinit var carsService : CarsService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val cars = MutableLiveData<List<Car>>()
    val carLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()


    fun refresh(){
        fetchCarsFromWeb()
    }

    private fun fetchCarsFromWeb() {
        loading.value = true
        disposable.add(
            carsService.getCars()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<CarfaxJsonResponse>() {

                    override fun onError(e: Throwable?) {
                        loadSavedCars()
                    }

                    override fun onSuccess(value: CarfaxJsonResponse?) {
                        val carListings = value?.listings
                        cars.value = carListings ?: arrayListOf()
                        carLoadError.value = false
                        loading.value = false
                        saveCarsLocally()
                    }

                })
        )
    }

    private fun loadSavedCars() {
        viewModelScope.launch {
            loading.value = true
            try {
                cars.value = datasource.getAllCars()
                loading.value = false
            } catch (e: Throwable) {
                loading.value = false
                carLoadError.value = true
            }
        }
    }

    private fun saveCarsLocally() {
        if (cars.value != null) {
            viewModelScope.launch {
                datasource.saveCars(cars.value!!)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}