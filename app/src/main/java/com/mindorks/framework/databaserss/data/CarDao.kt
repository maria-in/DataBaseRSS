package com.mindorks.framework.databaserss.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)

    @Query("SELECT * FROM car_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<Car>>
}