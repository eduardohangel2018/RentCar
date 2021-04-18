package com.android.rentmycar_v1.service.constants

import android.provider.BaseColumns

class DatabaseConstants private constructor() {

    object CARS {
        const val TABLE_NAME = "Cars"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val MODEL = "model"
            const val DESCRIPTION = "desc"
            const val PRICE = "price"
            const val CATEGORY = "category"
            const val STATUS = "status"
        }
    }
}