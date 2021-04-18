package com.android.rentmycar_v1.service.constants

class CarConstants private constructor() {
    companion object {
        val carId = "carId"
    }

    object FILTER_STATUS {
        const val all = 0
        const val available = 1
        const val unavailable = 2
    }
}