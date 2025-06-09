package com.example.lab3.appointments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.data.local.Appointment
import com.example.lab3.R

//Адаптер відображає дані згідно із масивом Стрічок
class  ItemAdapter(private var items: List<Appointment>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView
        val itemTime: TextView
        val itemNumber: TextView

        init {
// тут можна визначити обробник подій для UI елементів ViewHolder
            itemName = view.findViewById(R.id.itemText)
            itemTime = view.findViewById(R.id.itemTime)
            itemNumber = view.findViewById(R.id.itemNumber)


        }
    }


    // Створення нових View (викликається LayoutManager). На початку роботи у пам”яті відсутні елементи, які можна перевикористовувати. Тому вони генеруються тут.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):
            ViewHolder {
// Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)
        return ViewHolder(view)
    }


    //Заміна вмісту View (викликається LayoutManager) - якщо ми перевикористовуємо елемент, то варто затерти старі дані і відобразити нові.
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
// Отримайте елемент зі свого набору даних для цій позиції елементу та оновіть дані цього елементу
        viewHolder.itemName.text = items[position].name
        viewHolder.itemTime.text = items[position].time
        viewHolder.itemNumber.text = items[position].phone

    }

    fun updateItems(newItems: List<Appointment>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    // розмір вашого набору даниx, щоб знати скільки елементів треба відобразити.
    override fun getItemCount() = items.size


}