package com.example.lab3.data.remote

import com.example.lab3.data.local.Appointment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface AppointmentApi {
    @GET("appointments")
    suspend fun getAppointments(): List<Appointment>

    @POST("appointments")
    suspend fun addAppointment(@Body appointment: Appointment): Appointment

    @DELETE("appointments/{id}")
    suspend fun deleteAppointment(@Path("id") id: Int): Response<Unit>
}
