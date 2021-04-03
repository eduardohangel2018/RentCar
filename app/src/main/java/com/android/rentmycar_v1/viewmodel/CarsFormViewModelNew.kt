package com.android.rentmycar_v1.viewmodel

import com.android.rentmycar_v1.service.repository.CarRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CarsFormViewModelNew(application: Application) :  AndroidViewModel(application){

    private val mContext = application.applicationContext
    private val mCarsRepository: CarRepository = CarRepository.getInstance(mContext)
}