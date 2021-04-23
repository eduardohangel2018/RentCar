package com.android.rentmycar_v1.service.repository

import android.content.SharedPreferences
import androidx.core.content.edit

private const val CHAVE_LOGADO = "LOGADO"

class LoginRepository(private val preferences: SharedPreferences) {

    fun login() = salva(true)

    fun logout() = salva(false)

    fun isLogged(): Boolean = preferences
        .getBoolean(CHAVE_LOGADO, false)

    private fun salva(estado: Boolean) {
        preferences.edit {
            putBoolean(CHAVE_LOGADO, estado)
        }
    }

}