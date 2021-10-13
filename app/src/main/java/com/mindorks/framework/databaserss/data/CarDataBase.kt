package com.mindorks.framework.databaserss.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarDataBase: RoomDatabase(){
    abstract fun carDao(): CarDao
}
