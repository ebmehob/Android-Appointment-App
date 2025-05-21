package com.example.lab3.uii

import com.example.lab3.Appointment

interface SecondContract {
    interface View {
        fun displayAppointments(appointment: List<Appointment>)
        fun displayError()
    }

    interface Presenter{
        fun loadData()
    }
}