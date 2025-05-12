package com.example.lab3

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Appointment::class], version = 1)
abstract class AppointmentDatabase : RoomDatabase(){

    abstract fun getAppointmentDao() : AppointmentDao
}