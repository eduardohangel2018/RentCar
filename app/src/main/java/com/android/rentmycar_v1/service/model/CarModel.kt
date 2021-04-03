package com.android.rentmycar_v1.service.model

class CarModel(val id: Int = 0,
               var name: String,
               var model: String,
               var desc: String,
               var price: Double,
               var category: String,
               var status: Boolean)