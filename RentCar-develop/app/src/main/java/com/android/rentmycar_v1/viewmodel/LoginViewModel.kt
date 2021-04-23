package com.android.rentmycar_v1.viewmodel

import androidx.lifecycle.ViewModel
import com.android.rentmycar_v1.service.repository.LoginRepository
import com.android.rentmycar_v1.view.fragment.LoginFragment
import kotlin.reflect.KProperty

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    fun login(){
        repository.login()
    }

    fun logout() {
        repository.logout()
    }

    fun isLogged(): Boolean = repository.isLogged()

    fun notLogin(): Boolean = !isLogged()

}