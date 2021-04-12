package com.android.rentmycar_v1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rentmycar_v1.service.model.CarModel
import com.android.rentmycar_v1.service.repository.CarRepository

class AllCarsViewModel(application: Application) : AndroidViewModel(application) {


    private val mCarsRepository = CarRepository.getInstance(application.applicationContext)

    private val mCarsList = MutableLiveData<List<CarModel>>()
    val carList: MutableLiveData<List<CarModel>> = mCarsList

    fun load() {
        // Busco e Atribuo a lista
        mCarsList.value = mCarsRepository.getAll()
    }

    fun delete(id: Int) {
        mCarsRepository.delete(id)
    }
}