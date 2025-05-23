package com.example.lab3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
//Адаптер відображає дані згідно із масивом Стрічок
class TimeAdapter(private val context: Context, private val timeText: Array<String>) :
    RecyclerView.Adapter<TimeAdapter.ViewHolder>() {
    private var selectedPosition = RecyclerView.NO_POSITION

    /**
     * Створено власну реалізацію типу ViewHolder. У цьому випадку це тільки 1
    текстовий елемент.
     * (custom ViewHolder)
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val timeView: TextView
        init {
// тут можна визначити обробник подій для UI елементів ViewHolder
            timeView = view.findViewById(R.id.timeText)

            itemView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = getBindingAdapterPosition()

                // Notify changes to update previous and new selected items
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
            }
        }
    }
    // Створення нових View (викликається LayoutManager). На початку роботи у пам”яті відсутні елементи, які можна перевикористовувати. Тому вони генеруються тут.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):
            ViewHolder {
// Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.time, viewGroup, false)
        return ViewHolder(view)
    }

    //Заміна вмісту View (викликається LayoutManager) - якщо ми перевикористовуємо елемент, то варто затерти старі дані і відобразити нові.
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
// Отримайте елемент зі свого набору даних для цій позиції елементу та оновіть дані цього елементу
        viewHolder.timeView.text = timeText[position]

        viewHolder.itemView.background = ContextCompat.getDrawable(context,
            if (position == selectedPosition) R.drawable.backselected else R.drawable.back)
    }
    // розмір вашого набору даниx, щоб знати скільки елементів треба відобразити.
    override fun getItemCount() = timeText.size

    fun getSelectedTime(): String {
        return timeText.getOrNull(selectedPosition) ?: ""
    }

}