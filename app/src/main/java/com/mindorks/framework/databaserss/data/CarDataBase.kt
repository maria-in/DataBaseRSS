package com.mindorks.framework.databaserss.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarDataBase: RoomDatabase(){

    abstract fun carDao(): CarDao

    companion object{
        @Volatile
        private var INSTANCE: CarDataBase? = null

        fun getDatabase(context: Context): CarDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDataBase::class.java,
                    "car_data"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}