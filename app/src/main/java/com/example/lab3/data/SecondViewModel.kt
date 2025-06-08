package com.example.lab3.uii

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lab3.data.local.Appointment
import com.example.lab3.data.model.IDataSource
import com.example.lab3.di.DiHelper

class SecondViewModel : ViewModel() {

    private val service: IDataSource = DiHelper.getService()

    val appointments: LiveData<List<Appointment>> by lazy {
        service.getLocalAppointments()
    }
}
