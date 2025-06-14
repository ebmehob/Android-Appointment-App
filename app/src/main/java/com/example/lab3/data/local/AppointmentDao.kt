package com.example.lab3.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM APPOINTMENT")
    fun getAllAppointment() : LiveData<List<Appointment>>

    @Query("SELECT * FROM APPOINTMENT where date = :date")
    fun getAppointments(date: String) : List<Appointment>

    @Insert
    fun addAppointment(appointment: Appointment)

    @Query("Delete from Appointment where id = :id")
    fun deleteAppointment(id: Int)

    @Update
    fun updateAppointment(appointment: Appointment)

    @Query("SELECT * FROM Appointment WHERE date = :date")
    fun getAppointmentsLive(date: String): LiveData<List<Appointment>>

    @Query("DELETE FROM Appointment")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(appointments: List<Appointment>)
}