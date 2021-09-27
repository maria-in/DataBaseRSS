package com.mindorks.framework.databaserss.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_data")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val mark: String,
    val model: String,
    val year: Int
)