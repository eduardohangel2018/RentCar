package com.android.rentmycar_v1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rentmycar_v1.service.constants.CarConstants
import com.android.rentmycar_v1.service.model.CarModel
import com.android.rentmycar_v1.service.repository.CarRepository

class  CarsViewModel(application: Application) : AndroidViewModel(application) {


    private val mCarsRepository = CarRepository.getInstance(application.applicationContext)

    private val mCarsList = MutableLiveData<List<CarModel>>()
    val carList: MutableLiveData<List<CarModel>> = mCarsList

    fun load(status: Int) {

        if (status == CarConstants.FILTER_STATUS.all) {
            mCarsList.value = mCarsRepository.getAll()
        } else if (status == CarConstants.FILTER_STATUS.available) {
            mCarsList.value = mCarsRepository.getAvailable()
        } else {
            mCarsList.value = mCarsRepository.getUnavailable()
        }
    }

    fun delete(id: Int) {
        mCarsRepository.delete(id)
    }
}