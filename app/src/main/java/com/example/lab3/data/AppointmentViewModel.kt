package com.example.roomapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lab3.data.AppointmentRepository
import com.example.lab3.data.local.Appointment
import com.example.lab3.data.local.AppointmentDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppointmentViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Appointment>>
    private val repository: AppointmentRepository

    init {
        val userDao = AppointmentDatabase.getDatabase(application).getAppointmentDao()
        repository = AppointmentRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addAppointment(appointment: Appointment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAppointment(appointment)
        }
    }

    fun getAppointments(date: String): LiveData<List<Appointment>> {
        return repository.getAppointmentsLive(date)
    }

    fun getAllAppointments(): LiveData<List<Appointment>> {
        return repository.getAllAppointments()
    }

}