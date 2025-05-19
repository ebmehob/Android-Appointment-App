package com.example.lab3

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM APPOINTMENT")
    fun getAllAppointment() : List<Appointment>

    @Query("SELECT * FROM APPOINTMENT where date = :date")
    fun getAppointments(date: String) : List<Appointment>

    @Insert
    fun addAppointment(appointment: Appointment)

    @Query("Delete from Appointment where id = :id")
    fun deleteAppointment(id: Int)

    @Update
    fun updateAppointment(appointment: Appointment)
}