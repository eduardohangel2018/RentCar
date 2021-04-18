package com.android.rentmycar_v1.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.rentmycar_v1.R
import com.android.rentmycar_v1.service.model.CarModel
import com.android.rentmycar_v1.view.listener.CarListener
import kotlinx.android.synthetic.main.activity_car_form.view.*
// Armazena referência dos elementos de interface
class CarsViewHolder(itemView: View, private val listener: CarListener) : RecyclerView.ViewHolder(itemView) {
//    private val listener: CarListener

    fun bind(car: CarModel) {

        // Obter os elementos da interface
        val nameRow = itemView.findViewById<TextView>(R.id.nameRow)
        nameRow.setText(car.name)
        val modelRow = itemView.findViewById<TextView>(R.id.modelRow)
        modelRow.setText(car.model)
        val descRow = itemView.findViewById<TextView>(R.id.descRow)
        descRow.setText(car.desc)
        val priceRow = itemView.findViewById<TextView>(R.id.priceRow)
        priceRow.setText(car.price)
        val categoryRow = itemView.findViewById<TextView>(R.id.categoryRow)
        categoryRow.setText(car.category)
        val photoRow = itemView.findViewById<ImageView>(R.id.photoRow)

        photoRow.setOnClickListener {
            listener.onClick(car.id)
        }

        // Delete
        photoRow.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção do Carro")
                .setMessage("Deseja Remove o Carro?")
                .setPositiveButton(R.string.remover) { dialog, which ->
                    listener.onDelete(car.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()
            true
        }
    }
}