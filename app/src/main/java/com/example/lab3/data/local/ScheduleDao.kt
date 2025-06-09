package com.example.lab3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM Schedule")
    fun getAllSchedules() : List<Appointment>

    @Query("SELECT * FROM Schedule where doctor = :doctor")
    fun getAppointments(doctor: String) : Schedule

    @Insert
    fun addAppointment(schedule: Schedule)

    @Query("Delete from Schedule where doctor = :doctor")
    fun deleteAppointment(doctor: String)

    @Update
    fun updateAppointment(schedule: Schedule)
}