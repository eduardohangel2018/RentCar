package com.android.rentmycar_v1.service.di

import com.android.rentmycar_v1.service.repository.FirebaseAuth
import com.android.rentmycar_v1.service.repository.LoginRepository
import com.android.rentmycar_v1.viewmodel.LoginViewModel
import com.android.rentmycar_v1.viewmodel.RegisterUserViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.math.BigDecimal

private const val NOME_BANCO_DE_DADOS = "cars_new.db"


val daoModule = module {
    single<LoginRepository> { LoginRepository(get()) }
    single<FirebaseAuth> { FirebaseAuth(get()) }
}

val viewModule = module {
}

val viewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<RegisterUserViewModel> { RegisterUserViewModel(get()) }
}

val firebaseModule = module {
    single<com.google.firebase.auth.FirebaseAuth> { Firebase.auth }
}