package com.mindorks.framework.databaserss.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CarViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Car>>
    private val repository: CarRepository

    init {
        // https://developer.android.com/training/data-storage/room
        // See usage
        val database =
            Room.databaseBuilder(application, CarDataBase::class.java, "car_data").build()
        val carDao = database.carDao()
        repository = CarRepository(carDao)
        readAllData = repository.readAllData
    }

    fun addCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCar(car)
        }
    }
}
