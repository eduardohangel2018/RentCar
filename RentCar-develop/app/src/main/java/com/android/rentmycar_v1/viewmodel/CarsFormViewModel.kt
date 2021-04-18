package com.android.rentmycar_v1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.rentmycar_v1.service.model.CarModel
import com.android.rentmycar_v1.service.repository.CarRepository

class CarsFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mCarsRepository: CarRepository = CarRepository.getInstance(mContext)

    private var mSaveCar = MutableLiveData<Boolean>()
    val saveCar: LiveData<Boolean> = mSaveCar

    // Carrega os carros
    private var mCar = MutableLiveData<CarModel>()
    val car: LiveData<CarModel> = mCar

    // Salva Carro
    fun save(
        id: Int,
        name: String,
        model: String,
        desc: String,
        price: String,
        category: String,
        status: Boolean) {
        val car = CarModel(id, name, model, desc, price, category, status)
        if (id == 0){
            mSaveCar.value = mCarsRepository.save(car)
        } else {
            mSaveCar.value = mCarsRepository.update(car)
        }
    }

    // Carrega Carro que será igual ao repositório passando o id do carro
    fun load(id: Int) {
        mCar.value = mCarsRepository.get(id)
    }

//    fun update(mCarId: Int, name: String, model: String, price: String, desc: String, category: String, status: Boolean) {
//
//    }
}