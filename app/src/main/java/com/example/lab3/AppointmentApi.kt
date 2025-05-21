package com.example.lab3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AppointmentApi {

    @GET ("b/6828b0898a456b79669fb795")
    fun getAppointments(@Header("X-MASTER-KEY") secretKey: String): Call<Wrapper>
}