package com.example.lab3.appointments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.data.local.Appointment

class ItemAdapter(private var items: List<Appointment>,
                  private val onLongClick: (Appointment) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.itemText)
        val itemTime: TextView = view.findViewById(R.id.itemTime)
        val itemNumber: TextView = view.findViewById(R.id.itemNumber)

        init {
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onLongClick(items[position])
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = items[position]
        holder.apply {
            itemName.text = appointment.name
            itemTime.text = appointment.time
            itemNumber.text = appointment.phone
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Appointment>) {
        items = newItems
        notifyDataSetChanged()
    }
}
