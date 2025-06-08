package com.example.lab3.data

import androidx.lifecycle.LiveData
import com.example.lab3.data.local.Appointment
import com.example.lab3.data.local.AppointmentDao
import com.example.lab3.data.model.IDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalAppointmentDataSource(private val dao: AppointmentDao) : IDataSource {

    override fun getLocalAppointments(): LiveData<List<Appointment>> {
        return dao.getAllAppointment() // Room DAO
    }

}
