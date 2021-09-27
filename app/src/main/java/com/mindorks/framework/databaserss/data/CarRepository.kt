package com.mindorks.framework.databaserss.data

import androidx.lifecycle.LiveData

class CarRepository(private val carDao: CarDao) {
    val readAllData: LiveData<List<Car>> = carDao.readAllData()

    suspend fun addCar(car: Car){
        carDao.addCar(car)
    }
}