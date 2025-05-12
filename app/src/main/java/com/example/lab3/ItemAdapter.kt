package com.example.lab3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//Адаптер відображає дані згідно із масивом Стрічок
class ItemAdapter(private val itemText: Array<String>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    /**
     * Створено власну реалізацію типу ViewHolder. У цьому випадку це тільки 1
    текстовий елемент.
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemView: TextView

        init {
// тут можна визначити обробник подій для UI елементів ViewHolder
            itemView = view.findViewById(R.id.itemText)

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
        viewHolder.itemView.text = itemText[position]
    }
    // розмір вашого набору даниx, щоб знати скільки елементів треба відобразити.
    override fun getItemCount() = itemText.size
}