package com.android.rentmycar_v1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.rentmycar_v1.service.repository.FirebaseAuth

class RegisterUserViewModel(private val repository: FirebaseAuth) : ViewModel() {

    fun register(email: String, password: String): LiveData<Boolean> {
        return repository.register(email, password)
    }
}