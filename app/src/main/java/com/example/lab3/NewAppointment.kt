package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.data.local.Appointment
import com.example.lab3.uii.Appointments
import com.example.roomapp.data.AppointmentViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class NewAppointment : ComponentActivity() {
    private lateinit var selectedDate: String
    private var timeAdapterglobal: TimeAdapter? = null
    private lateinit var appViewModel: AppointmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appViewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)


        val formatter =
            DateTimeFormatter.ofPattern("E") // "E" gives short day names (Mon, Tue, etc.)
        val dayText = Array(30) { i -> LocalDate.now().plusDays(i.toLong()).format(formatter) }
        val dayNumb =
            Array(30) { i -> (LocalDate.now().plusDays(i.toLong()).dayOfMonth).toString() }

// створення адаптеру
        val customAdapter = CustomAdapter(this, dayText, dayNumb)
        val recyclerView: RecyclerView = findViewById(R.id.date_view)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)




        customAdapter.setOnDateSelectedListener(object : CustomAdapter.OnDateSelectedListener {
            override fun onDateSelected(date: String) {
                selectedDate = date
                Log.d("SelectedDate", date)

                // Спостерігай за LiveData
                appViewModel.getAppointments(date).observe(this@NewAppointment) { appointments ->

                    val times =
                        (8..22).map { hour -> String.format("%02d:00", hour) }.toTypedArray()
                    val bookedTimes = appointments.map { it.time }.toSet()
                    val availableTimes =
                        times.filter { time -> time !in bookedTimes }.toTypedArray()

                    val timeAdapter = TimeAdapter(this@NewAppointment, availableTimes)
                    timeAdapterglobal = timeAdapter
                    val timerecyclerView: RecyclerView = findViewById(R.id.time_view)
                    timerecyclerView.adapter = timeAdapter
                    timerecyclerView.layoutManager =
                        GridLayoutManager(this@NewAppointment, 4, GridLayoutManager.VERTICAL, false)
                }
            }
        })


        val toSecond: Button = findViewById(R.id.backbutton)
        toSecond.setOnClickListener {
            val intent = Intent(this, Appointments::class.java)
            startActivity(intent)
        }

        val insertAppointment: Button = findViewById(R.id.button1)
        insertAppointment.setOnClickListener {
            val inputName: String =
                findViewById<EditText>(R.id.nameinput).text.toString() // Get text as String
            val inputPhone: String =
                findViewById<EditText>(R.id.phoneinput).text.toString() // Get text as String
            val selectedTime: String = timeAdapterglobal?.getSelectedTime() ?: ""

            if (inputName == "" || inputPhone == "" || selectedTime == "" || selectedDate == "") {
                Toast.makeText(
                    MainActivity@ this, "Missing input",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val newAppointment = Appointment(
                    date = selectedDate,
                    time = selectedTime,
                    name = inputName,
                    phone = inputPhone
                )

                appViewModel.addAppointment(newAppointment)

                val intent = Intent(this, Appointments::class.java)
                startActivity(intent)

            }

        }

    }
}
