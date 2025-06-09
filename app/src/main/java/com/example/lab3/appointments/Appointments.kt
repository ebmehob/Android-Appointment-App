package com.example.lab3.appointments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab3.data.local.Appointment
import com.example.lab3.newAppointment.NewAppointment
import com.example.lab3.databinding.ScheduleBinding
import com.example.roomapp.data.AppointmentViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Appointments : ComponentActivity() {

    private lateinit var binding: ScheduleBinding
    private lateinit var itemAdapter: ItemAdapter
    private var fullAppointmentList: List<Appointment> = emptyList()

//    private lateinit var viewModel: SecondViewModel
    private lateinit var viewModel: AppointmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Init ViewModel
        viewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)


        // Init RecyclerView
        itemAdapter = ItemAdapter(emptyList())
        binding.itemView.adapter = itemAdapter
        binding.itemView.layoutManager = LinearLayoutManager(this)

        // Observe appointments
        viewModel.getAllAppointments().observe(this@Appointments) { appointmentList ->
            fullAppointmentList = appointmentList
            val today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            val filtered = appointmentList.filter { it.date == today }
            itemAdapter.updateItems(filtered)
        }

        // Calendar filtering
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = String.format("%02d.%02d.%04d", dayOfMonth, month + 1, year)
            val filtered = fullAppointmentList.filter { it.date == selectedDate }
            itemAdapter.updateItems(filtered)
        }

        // Navigation
        binding.button2.setOnClickListener {
            startActivity(Intent(this, NewAppointment::class.java))
        }
    }
}
