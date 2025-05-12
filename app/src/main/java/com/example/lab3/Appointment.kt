package com.example.lab3

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Appointment (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val time: String,
    val name: String,
    val phone: String
)