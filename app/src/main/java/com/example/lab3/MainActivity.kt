package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dayText = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val dayNumb = arrayOf("1", "2", "3", "4", "5", "6", "7")
// створення адаптеру
        val customAdapter = CustomAdapter(this, dayText, dayNumb)
        val recyclerView: RecyclerView = findViewById(R.id.date_view)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val times = (8..22).map { hour -> String.format("%02d:00", hour) }.toTypedArray()
// створення адаптеру
        val timeAdapter = TimeAdapter(this, times)
        val timerecyclerView: RecyclerView = findViewById(R.id.time_view)
        timerecyclerView.adapter = timeAdapter
        timerecyclerView.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)

        val toSecond: Button  = findViewById(R.id.button1)

        toSecond.setOnClickListener{
            val intent = Intent(this, SecondScreen::class.java)
            startActivity(intent)
        }


//        createDB()
//        testDB()
    }

    lateinit var db: AppointmentDatabase

    private fun createDB(){
        db = Room.databaseBuilder(
            applicationContext,
            AppointmentDatabase::class.java, "testDB"
        ).allowMainThreadQueries().build()
    }

    private fun testDB(){
//        insert()
        read()
        update()
        read()
        delete()
        read()
    }

    fun insert(){
        message("ins")
        val dao = db.getAppointmentDao()

        var appointment = Appointment(1, "12.05.2025", "12:00", "John", "3455")
        dao.addAppointment(appointment)
        appointment = Appointment(2, "11.05.1925", "12:00", "Tyrion", "3455")
        dao.addAppointment(appointment)


    }

    fun read(){
        val dao = db.getAppointmentDao()

        val app = dao.getAllAppointment()

        for (a in app) {
            message("$a")
        }
    }

    fun delete(){
        message("del")
        val dao = db.getAppointmentDao()

        dao.deleteAppointment(1)
//        dao.deleteAppointment(2)
//        dao.deleteAppointment(3)
//        dao.deleteAppointment(4)

    }

    fun update(){
        message("upd")
        val dao = db.getAppointmentDao()

        val appointment = Appointment(1, "12.05.2025", "12:00", "John", "1111111111")
        dao.updateAppointment(appointment)
    }


    fun message(m: String){
        Log.d("Test", m)
    }
}