package com.example.lab3.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Schedule (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val from: String,
    val to: String,
    val days: String,
    val doctor: String
)