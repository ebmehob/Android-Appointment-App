package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.lab3.data.local.Appointment
import com.example.lab3.data.local.AppointmentDatabase
import com.example.lab3.uii.SecondScreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {
    private lateinit var selectedDate: String
    private var timeAdapterglobal: TimeAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formatter = DateTimeFormatter.ofPattern("E") // "E" gives short day names (Mon, Tue, etc.)
        val dayText = Array(30) { i -> LocalDate.now().plusDays(i.toLong()).format(formatter) }
        val dayNumb = Array(30) { i -> (LocalDate.now().plusDays(i.toLong()).dayOfMonth).toString() }

// створення адаптеру
        val customAdapter = CustomAdapter(this, dayText, dayNumb)
        val recyclerView: RecyclerView = findViewById(R.id.date_view)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        createDB()
//        delete()
//        insert()




        customAdapter.setOnDateSelectedListener(object : CustomAdapter.OnDateSelectedListener {
            override fun onDateSelected(date: String) {
                selectedDate = date
                Log.d("SelectedDate", date)

                val appointments = read(date)

                val times = (8..22).map { hour -> String.format("%02d:00", hour) }.toTypedArray()

                val bookedTimes = appointments.map { it.time }.toSet() // e.g., ["10:00", "14:00"]
                val availableTimes = times.filter { time -> time !in bookedTimes }.toTypedArray()


                val timeAdapter = TimeAdapter(this@MainActivity, availableTimes)
                timeAdapterglobal=timeAdapter
                val timerecyclerView: RecyclerView = findViewById(R.id.time_view)
                timerecyclerView.adapter = timeAdapter
                timerecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 4, GridLayoutManager.VERTICAL, false)
            }
        })



//
//        val times = (8..22).map { hour -> String.format("%02d:00", hour) }.toTypedArray()
//// створення адаптеру
//        val timeAdapter = TimeAdapter(this, times)
//        val timerecyclerView: RecyclerView = findViewById(R.id.time_view)
//        timerecyclerView.adapter = timeAdapter
//        timerecyclerView.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)


        val toSecond: Button  = findViewById(R.id.backbutton)
        toSecond.setOnClickListener{
            val intent = Intent(this, SecondScreen::class.java)
            startActivity(intent)
        }

        val insertAppointment: Button  = findViewById(R.id.button1)
        insertAppointment.setOnClickListener{
            val inputName: String = findViewById<EditText>(R.id.nameinput).text.toString() // Get text as String
            val inputPhone: String = findViewById<EditText>(R.id.phoneinput).text.toString() // Get text as String
            val selectedTime: String = timeAdapterglobal?.getSelectedTime() ?: ""

            if (inputName == "" || inputPhone == "" || selectedTime == "" || selectedDate == "" ){
                Toast.makeText(MainActivity@ this, "R.string.failed_load_data",
                    Toast.LENGTH_LONG).show()
            }
            else {
                val newAppointment = Appointment(
                    date = selectedDate,
                    time = selectedTime,
                    name = inputName,
                    phone = inputPhone
                )

                insert(newAppointment)

                val intent = Intent(this, SecondScreen::class.java)
                startActivity(intent)

            }

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

//    private fun testDB(){
////        insert()
//        read()
//        update()
//        read()
//        delete()
//        read()
//    }

    fun insert(appointment: Appointment){
        message("ins")
        val dao = db.getAppointmentDao()

//        var appointment = Appointment(date = "30.05.2025", time = "12:00", name = "John", phone = "3455")
        dao.addAppointment(appointment)
//        appointment = Appointment(date = "30.05.2025", time = "09:00", name = "Tyrion", phone = "34872455")
//        dao.addAppointment(appointment)


    }

    fun read(date: String): List<Appointment> {
        val dao = db.getAppointmentDao()

        val app = dao.getAppointments(date)

        for (a in app) {
            message("$a")
        }

        return app
    }

    fun delete(){
        message("del")
        val dao = db.getAppointmentDao()

        dao.deleteAppointment(1)
        dao.deleteAppointment(2)
//        dao.deleteAppointment(3)
//        dao.deleteAppointment(4)

    }

    fun update(){
        message("upd")
        val dao = db.getAppointmentDao()

        val appointment = Appointment(1, "12.05.2025", "12:00", "John", "1111111111")
        dao.updateAppointment(appointment)
    }


    fun getFreeTime(){

    }


    fun message(m: String){
        Log.d("Test", m)
    }
}