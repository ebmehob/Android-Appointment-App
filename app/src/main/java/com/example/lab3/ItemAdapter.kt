package com.example.lab3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.databinding.ItemBinding
import com.example.lab3.databinding.ScheduleBinding

//Адаптер відображає дані згідно із масивом Стрічок
class ItemAdapter(private val itemText: List<Appointment>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

////////////////////////////////////////////


//
//    inner class AppointmentViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
//
//
//    private val diffCallback = object : DiffUtil.ItemCallback<Appointment>() {
//        override fun areItemsTheSame(oldItem: Appointment, newItem: Appointment): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: Appointment, newItem: Appointment): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    private val differ = AsyncListDiffer(this, diffCallback)
//    var appointments: List<Appointment>
//        get() = differ.currentList
//        set(value) { differ.submitList(value) }
//
//    override fun getItemCount() = appointments.size
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
//        return AppointmentViewHolder(ItemBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        ))
//    }
//
//    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
//        holder.binding.apply {
//            val app = appointments[position]
//            itemText.text = app.name
//            itemTime.text = app.time
//            itemNumber.text = app.phone
//        }
//    }
//



    /////////////////////////////

    /**
     * Створено власну реалізацію типу ViewHolder. У цьому випадку це тільки 1
    текстовий елемент.
     * (custom ViewHolder)
     */
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
        viewHolder.itemName.text = itemText[position].name
        viewHolder.itemTime.text = itemText[position].time
        viewHolder.itemNumber.text = itemText[position].phone

    }


    // розмір вашого набору даниx, щоб знати скільки елементів треба відобразити.
    override fun getItemCount() = itemText.size


}

