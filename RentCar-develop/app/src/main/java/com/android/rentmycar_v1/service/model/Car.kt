package com.android.rentmycar_v1.service.model

import java.util.HashMap

class Car {

    var id: String? = null
    var name: String? = ""
    var model: String? = ""
    var price: String? = ""
    var description: String? = ""
    var category: String? = ""
    val image: String? = null

    constructor() {}

    constructor(id: String, name: String, model: String, price: String, description: String, category: String) {
        this.id = id
        this.name = name
        this.model = model
        this.price = price
        this.description = description
        this.category = category
    }

    constructor(name: String, model: String, price: String, description: String, category: String) {
        this.name = name
        this.model = model
        this.price = price
        this.description = description
        this.category = category
    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("name", name!!)
        result.put("model", model!!)
        result.put("price", price!!)
        result.put("description", description!!)
        result.put("category", category!!)

        return result
    }
}