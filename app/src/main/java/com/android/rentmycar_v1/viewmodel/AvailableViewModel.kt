package com.android.rentmycar_v1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AvailableViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Carros Disponíveis"
    }
    val text: LiveData<String> = _text
}