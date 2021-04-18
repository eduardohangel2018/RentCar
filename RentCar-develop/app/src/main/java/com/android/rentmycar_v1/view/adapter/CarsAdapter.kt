package com.android.rentmycar_v1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.rentmycar_v1.R
import com.android.rentmycar_v1.service.model.CarModel
import com.android.rentmycar_v1.view.listener.CarListener
import com.android.rentmycar_v1.view.viewholder.CarsViewHolder
import com.google.firebase.firestore.FirebaseFirestore

class  CarsAdapter : RecyclerView.Adapter<CarsViewHolder>() {

    private var mCarList: List<CarModel> = arrayListOf()
    private lateinit var mListener: CarListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        // O onCreateViewHolder passa linha por linha tratando através do carItem
        val carItem = LayoutInflater.from(parent.context).inflate(R.layout.row_car, parent, false)
        return CarsViewHolder(carItem, mListener)
    }


    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.bind(mCarList[position])
    }

    // Pega a quantidade de registros
    override fun getItemCount(): Int {
        return mCarList.count()
    }

    fun updateCars(list: List<CarModel>) {
        mCarList = list
        notifyDataSetChanged()
    }

    // Preenche o Listener
    fun attachListener(listener: CarListener){
        mListener = listener
    }
}