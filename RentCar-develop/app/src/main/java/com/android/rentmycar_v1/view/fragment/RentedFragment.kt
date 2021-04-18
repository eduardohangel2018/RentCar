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

class RentedFragment : Fragment() {

    private lateinit var carsViewModel: CarsViewModel
    private val mAdapter: CarsAdapter = CarsAdapter()
    private lateinit var mListener: CarListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        carsViewModel =
            ViewModelProvider(this).get(CarsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_rented, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recyclerViewRented)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mListener = object : CarListener {
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
                carsViewModel.load(CarConstants.FILTER_STATUS.unavailable)
            }
        }
        mAdapter.attachListener(mListener)
        observer()
        return root
    }

    override fun onResume() {
        super.onResume()
        carsViewModel.load(CarConstants.FILTER_STATUS.unavailable)
    }

    // O viewLifeCycleOwner faz o papel do contexto no Fragment
    private fun observer() {
        carsViewModel.carList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateCars(it)
        })
    }
}