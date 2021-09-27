package com.mindorks.framework.databaserss.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCar(car: Car)

    @Query("SELECT * FROM car_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<Car>>
}