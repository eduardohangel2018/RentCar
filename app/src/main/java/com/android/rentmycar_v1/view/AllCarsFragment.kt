package com.android.rentmycar_v1.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.rentmycar_v1.R
import com.android.rentmycar_v1.service.constants.CarConstants
import com.android.rentmycar_v1.view.adapter.CarsAdapter
import com.android.rentmycar_v1.view.listener.CarListener
import com.android.rentmycar_v1.viewmodel.AllCarsViewModel
import com.google.firebase.firestore.FirebaseFirestore

class AllCarsFragment : Fragment() {

    private lateinit var allCarsViewModel: AllCarsViewModel
    private val mAdapter: CarsAdapter = CarsAdapter()
    private lateinit var mListener: CarListener

    // Firebase
//    companion object {
//        const val NAME = "name"
//        const val MODEL = "model"
//        const val PRICE = "price"
//        const val CATEGORY = "category"
//        var document = FirebaseFirestore.getInstance().collection("cars").document("rent")
//    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        allCarsViewModel = ViewModelProvider(this).get(AllCarsViewModel::class.java)
        // Variável que armazena a criação do layout
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        // 1. Obter a Recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recyclerViewAll)

        // 2. Define o Layout
        recycler.layoutManager = LinearLayoutManager(context)

        // 3. Define o Adapter - une dados aos elementos
        recycler.adapter = mAdapter

        mListener = object : CarListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, CarFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(CarConstants.carId, id)

                // Espera um Bundle
                intent.putExtras(bundle)

                // Inicializa a Intent
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                allCarsViewModel.delete(id)
                allCarsViewModel.load()
            }
        }
        mAdapter.attachListener(mListener)

        observer()

        return root
    }

    // Método que carrega mudanças após alter a Recycler View
    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        allCarsViewModel.load()
    }

    // O viewLifeCycleOwner faz o papel do contexto no Fragment
    private fun observer() {
        allCarsViewModel.carList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateCars(it)
        })
    }
}