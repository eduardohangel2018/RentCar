package com.android.rentmycar_v1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.rentmycar_v1.R
import com.android.rentmycar_v1.viewmodel.CarsViewModel
import com.google.firebase.firestore.FirebaseFirestore

class CarsFragment : Fragment() {

    private lateinit var carsViewModel: CarsViewModel

    companion object {
        const val NAME = "name"
        const val MODEL = "model"
        const val PRICE = "price"
        const val CATEGORY = "category"
        var document = FirebaseFirestore.getInstance().collection("cars").document("rent")
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        carsViewModel =
                ViewModelProvider(this).get(CarsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        carsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}