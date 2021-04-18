package com.android.rentmycar_v1.view.fragment

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
import com.android.rentmycar_v1.view.CarFormActivity
import com.android.rentmycar_v1.view.adapter.CarsAdapter
import com.android.rentmycar_v1.view.listener.CarListener
import com.android.rentmycar_v1.viewmodel.CarsViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class AllCarsFragment : Fragment() {

    private lateinit var carsViewModel: CarsViewModel
    private val mAdapter: CarsAdapter = CarsAdapter()
    private lateinit var mListener: CarListener

//    Firebase
    private var FirebaseDB: FirebaseFirestore? = null
    private var firestoreListener: ListenerRegistration? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        carsViewModel = ViewModelProvider(this).get(CarsViewModel::class.java)

        // Variável que armazena a criação do layout
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        // 1. Obter a Recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recyclerViewAll)

        // 2. Define o Layout
        recycler.layoutManager = LinearLayoutManager(context)

        // 3. Define o Adapter - une dados aos elementos
        recycler.adapter = mAdapter

        // Ao clicar no elemento abre uma tela para update
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
                carsViewModel.delete(id)
                carsViewModel.load(CarConstants.FILTER_STATUS.all)
            }
        }

        observer()
//        Return da Fragment
        return root
    }

    // Método que carrega mudanças após alter a Recycler View
    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        carsViewModel.load(CarConstants.FILTER_STATUS.all)
    }

    // O viewLifeCycleOwner faz o papel do contexto no Fragment
    // Cria os observables
    private fun observer() {
        carsViewModel.carList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateCars(it)
        })
    }
}