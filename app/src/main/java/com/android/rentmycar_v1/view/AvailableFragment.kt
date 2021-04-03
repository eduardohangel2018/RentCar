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
import com.android.rentmycar_v1.viewmodel.AvailableViewModel

class AvailableFragment : Fragment() {

    private lateinit var availableViewModel: AvailableViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        availableViewModel =
                ViewModelProvider(this).get(AvailableViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_available, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        availableViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}