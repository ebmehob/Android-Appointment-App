package com.example.lab3.uii

import android.util.Log
import com.example.lab3.Appointment
import com.example.lab3.data.model.IDataSource
import com.example.lab3.di.DiHelper

class SecondPresenter(val view: SecondContract.View) : SecondContract.Presenter {

    val service: IDataSource = DiHelper.getService()

    override fun loadData() {
        Log.d("API", "Load data")
        service.getLocalAppointments(object : IDataSource.AppointmentCallback {
            override fun onSuccess(appoinments: List<Appointment>) {
                view.displayAppointments(appoinments)
            }
            override fun onFailure() {
                view.displayError()
            }
        })
    }
}