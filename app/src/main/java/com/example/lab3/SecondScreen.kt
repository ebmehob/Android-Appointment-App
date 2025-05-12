package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule)

        val items = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

        val itemAdapter = ItemAdapter(items)
        val itemrecyclerView: RecyclerView = findViewById(R.id.item_view)
        itemrecyclerView.adapter = itemAdapter
        itemrecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val toFirst: Button = findViewById(R.id.button2)

        toFirst.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}