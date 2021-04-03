package com.android.rentmycar_v1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RentedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Estes carros estão alugados"
    }
    val text: LiveData<String> = _text
}