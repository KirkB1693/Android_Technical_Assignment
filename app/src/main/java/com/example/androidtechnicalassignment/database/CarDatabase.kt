package com.example.androidtechnicalassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidtechnicalassignment.model.Car

@Database(entities = [Car::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CarDatabase: RoomDatabase() {

    abstract val carDao: CarDao

    companion object {

        @Volatile
        private var INSTANCE: CarDatabase? = null

        fun getInstance(context: Context): CarDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CarDatabase::class.java,
                        "car_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}