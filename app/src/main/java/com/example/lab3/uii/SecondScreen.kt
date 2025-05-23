package com.example.lab3.uii

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.lab3.data.local.Appointment
import com.example.lab3.data.local.AppointmentDatabase
import com.example.lab3.MainActivity
import com.example.lab3.R
import com.example.lab3.data.model.AppointmentApiService
import com.example.lab3.databinding.ScheduleBinding
import com.example.lab3.data.model.IDataSource
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SecondScreen : ComponentActivity(), SecondContract.View{
    private lateinit var binding: ScheduleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var presenter: SecondContract.Presenter = SecondPresenter(this)

//        createDB()
//        loadData()
        presenter.loadData()



//        val today = LocalDate.now()
//        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
////        Log.d("sho", "Formatted date: ${today.format(formatter)}")

//        val appointments = read("${today.format(formatter)}")



//        val itemAdapter = ItemAdapter(appointments)
//        val itemrecyclerView: RecyclerView = findViewById(R.id.item_view)
//        itemrecyclerView.adapter = itemAdapter
//        itemrecyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//



//        binding.calendarView.setOnDateChangeListener {_, year, month, day ->
//            val date = ("%02d".format(day) + "." + "%02d".format(month+1) + "." + "%04d".format(year))
//
//
//            val appointments = read(date)
//
//            val itemAdapter = ItemAdapter(appointments)
//            val itemrecyclerView: RecyclerView = findViewById(R.id.item_view)
//            itemrecyclerView.adapter = itemAdapter
//            itemrecyclerView.layoutManager =
//                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        }



        val toFirst: Button = findViewById(R.id.button2)

        toFirst.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }



    override fun displayAppointments(apps: List<Appointment>) {

        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
//        Log.d("sho", "Formatted date: ${today.format(formatter)}")

        var appointments: List<Appointment> = emptyList()

        for (a in apps){
            if (a.date == "${today.format(formatter)}")
                appointments = appointments + a
        }

//        val appointments = read("${today.format(formatter)}")

        val itemAdapter = ItemAdapter(appointments)
        val itemrecyclerView: RecyclerView = findViewById(R.id.item_view)
        itemrecyclerView.adapter = itemAdapter
        itemrecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)




        binding.calendarView.setOnDateChangeListener {_, year, month, day ->

            val date = ("%02d".format(day) + "." + "%02d".format(month+1) + "." + "%04d".format(year))


            appointments = emptyList()

            for (a in apps){
                if (a.date == "$date")
                    appointments = appointments + a
            }


            val itemAdapter = ItemAdapter(appointments)
            val itemrecyclerView: RecyclerView = findViewById(R.id.item_view)
            itemrecyclerView.adapter = itemAdapter
            itemrecyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }


//        Log.d("API",
//            "${weather.name}")
//        Log.d("API",
//            "${weather.time}")
//        Log.d("API",
//            "${weather.phone}")
    }




    override fun displayError() {
        Log.d("API", "error loading data")
        Toast.makeText(MainActivity@ this, "R.string.failed_load_data",
            Toast.LENGTH_LONG).show()
    }











//
//
//    lateinit var db: AppointmentDatabase
//
//    private fun createDB(){
//        db = Room.databaseBuilder(
//            applicationContext,
//            AppointmentDatabase::class.java, "testDB"
//        ).allowMainThreadQueries()
//            .build()
//    }
//
////    private fun testDB(){
////        insert()
////        read()
////        update()
////        read()
////        delete()
////        read()
////    }
//
//    fun insert(){
//        message("ins")
//        val dao = db.getAppointmentDao()
//
////        var appointment = Appointment(1, "12.05.2025", "10:00", "John", "34551122")
////        dao.addAppointment(appointment)
//        var appointment = Appointment(2, "11.05.2025", "12:00", "Tyrion", "3455")
//        dao.addAppointment(appointment)
//
//
//    }
//
//    fun read(date: String): List<Appointment> {
//        val dao = db.getAppointmentDao()
//
//        val app = dao.getAppointments(date)
//
//        for (a in app) {
//            message("$a")
//        }
//
//        return app
//    }
//
//    fun delete(){
//        message("del")
//        val dao = db.getAppointmentDao()
//
//        dao.deleteAppointment(2)
////        dao.deleteAppointment(2)
////        dao.deleteAppointment(3)
////        dao.deleteAppointment(4)
//
//    }
//
//    fun update(){
//        message("upd")
//        val dao = db.getAppointmentDao()
//
//        val appointment = Appointment(1, "13.05.2025", "12:00", "John", "1111111111")
//
//        dao.updateAppointment(appointment)
//    }
//
//
//    fun message(m: String){
//        Log.d("Test", m)
//    }

}