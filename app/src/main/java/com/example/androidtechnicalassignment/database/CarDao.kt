package com.example.androidtechnicalassignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidtechnicalassignment.model.Car

@Dao
interface CarDao {

    /**
     * Insert an car in the database. If the car already exists, replace it.
     *
     * @param car the car to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCar(car: Car)

    /**
     * Insert a list of cars in the database. If the cars already exist, replace them.
     *
     * @param cars the list of cars to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCars(cars: List<Car>)

    /**
     * @return all cars.
     */
    @Query("SELECT * FROM car_table")
    suspend fun getAllCars(): List<Car>

    /**
     * @param carId the id of the car
     * @return the car object with the carId
     */
    @Query("SELECT * FROM car_table where id = :carId")
    suspend fun getCarById(carId: Long): Car?

    /**
     * Delete all cars.
     */
    @Query("DELETE FROM car_table")
    suspend fun deleteAllCars()

    /**
     * @param carId the id of the election
     * @return the car object with the carId
     */
    @Query("DELETE FROM car_table where id = :carId")
    suspend fun clearCarById(carId: Int)

}