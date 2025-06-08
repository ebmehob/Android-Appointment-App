package com.example.lab3.data.model

import androidx.lifecycle.LiveData
import com.example.lab3.data.local.Appointment

interface IDataSource {
    fun getLocalAppointments(): LiveData<List<Appointment>>
}