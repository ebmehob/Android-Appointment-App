package com.example.lab3.data

import androidx.lifecycle.LiveData
import com.example.lab3.App
import com.example.lab3.data.local.Appointment
import com.example.lab3.data.local.AppointmentDao
import com.example.lab3.data.remote.AppointmentApi

class AppointmentRepository (private val appointmentDao: AppointmentDao,
                             private val api: AppointmentApi) {
    val readAllData: LiveData<List<Appointment>> = appointmentDao.getAllAppointment()

    suspend fun addAppointment(appointment: Appointment){
        api.addAppointment(appointment)
        appointmentDao.addAppointment(appointment)
    }

    fun getAllAppointments() : LiveData<List<Appointment>>{
        return appointmentDao.getAllAppointment()
    }

    fun getAppointmentsLive(date: String): LiveData<List<Appointment>> {
        return appointmentDao.getAppointmentsLive(date)
    }

    suspend fun deleteAppointment(appointment: Appointment){
        api.deleteAppointment(appointment.id)
        appointmentDao.deleteAppointment(appointment.id)
    }

    suspend fun refreshAppointmentsFromRemote() {
        val remoteAppointments = api.getAppointments()
        appointmentDao.deleteAll()
        appointmentDao.insertAll(remoteAppointments)
    }
}