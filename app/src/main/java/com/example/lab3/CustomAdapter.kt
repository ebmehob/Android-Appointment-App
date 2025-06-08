package com.example.lab3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
//Адаптер відображає дані згідно із масивом Стрічок
class CustomAdapter(private val context: Context, private val dayText: Array<String>, private val dayNumb: Array<String>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val numbView: TextView

        init {
// тут можна визначити обробник подій для UI елементів ViewHolder
            textView = view.findViewById(R.id.dayText)
            numbView = view.findViewById(R.id.dayNumb)

            itemView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition

                // Notify changes to update previous and new selected items
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

                val today = java.time.LocalDate.now()
                val selectedDate = today.plusDays(selectedPosition.toLong())
                val formatter = java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy")
                val formattedDate = selectedDate.format(formatter)
                dateSelectedListener?.onDateSelected(formattedDate)
            }
        }
    }

// Створення нових View (викликається LayoutManager). На початку роботи у пам”яті відсутні елементи, які можна перевикористовувати. Тому вони генеруються тут.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):
            ViewHolder {
// Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(view)
    }

//Заміна вмісту View (викликається LayoutManager) - якщо ми перевикористовуємо елемент, то варто затерти старі дані і відобразити нові.
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
// Отримайте елемент зі свого набору даних для цій позиції елементу та оновіть дані цього елементу
                viewHolder.textView.text = dayText[position]
                viewHolder.numbView.text = dayNumb[position]

    viewHolder.itemView.background = ContextCompat.getDrawable(context,
        if (position == selectedPosition) R.drawable.backselected else R.drawable.back)
}

// розмір вашого набору даниx, щоб знати скільки елементів треба відобразити.
    override fun getItemCount() = dayText.size



    fun getSelectedDate(): String? {
        if (selectedPosition == RecyclerView.NO_POSITION) return null

        val today = java.time.LocalDate.now()
        val selectedDate = today.plusDays(selectedPosition.toLong())
        val formatter = java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return selectedDate.format(formatter)
    }



    private var dateSelectedListener: OnDateSelectedListener? = null

    fun setOnDateSelectedListener(listener: OnDateSelectedListener) {
        this.dateSelectedListener = listener
    }

    interface OnDateSelectedListener {
        fun onDateSelected(date: String)
    }

}