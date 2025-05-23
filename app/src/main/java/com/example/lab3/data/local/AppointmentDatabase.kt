package com.example.lab3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab3.Schedule

@Database(entities = [Appointment::class, Schedule::class], version = 2)
abstract class AppointmentDatabase : RoomDatabase(){

    abstract fun getAppointmentDao() : AppointmentDao
}