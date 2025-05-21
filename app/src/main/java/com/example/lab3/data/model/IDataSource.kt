package com.example.lab3.data.model

import com.example.lab3.Appointment

interface IDataSource {
    fun getLocalAppointments(callback: AppointmentCallback)

    interface AppointmentCallback {
        fun onSuccess(appoinment: List<Appointment>)
        fun onFailure()
    }
}