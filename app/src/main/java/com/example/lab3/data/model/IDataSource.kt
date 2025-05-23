package com.example.lab3.data.model

import com.example.lab3.data.local.Appointment

interface IDataSource {
    fun getLocalAppointments(callback: AppointmentCallback)

    interface AppointmentCallback {
        fun onSuccess(appoinment: List<Appointment>)
        fun onFailure()
    }
}