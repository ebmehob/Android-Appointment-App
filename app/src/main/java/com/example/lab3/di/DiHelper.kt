package com.example.lab3.di

import com.example.lab3.App
import com.example.lab3.data.LocalAppointmentDataSource
import com.example.lab3.data.model.IDataSource
import com.example.lab3.data.local.AppointmentDatabase


class DiHelper {

    companion object {
        fun getService(): IDataSource {
            val context = App.instance.applicationContext
            val db = AppointmentDatabase.getDatabase(context)
            return LocalAppointmentDataSource(db.getAppointmentDao())
        }


    }
}