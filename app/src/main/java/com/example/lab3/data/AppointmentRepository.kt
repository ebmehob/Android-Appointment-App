package com.example.lab3.data

import androidx.lifecycle.LiveData
import com.example.lab3.App
import com.example.lab3.data.local.Appointment
import com.example.lab3.data.local.AppointmentDao

class AppointmentRepository (private val appointmentDao: AppointmentDao) {
    val readAllData: LiveData<List<Appointment>> = appointmentDao.getAllAppointment()

    suspend fun addAppointment(appointment: Appointment){
        appointmentDao.addAppointment(appointment)
    }

    fun getAllAppointments() : LiveData<List<Appointment>>{
        return appointmentDao.getAllAppointment()
    }


    fun getAppointmentsLive(date: String): LiveData<List<Appointment>> {
        return appointmentDao.getAppointmentsLive(date)
    }
}