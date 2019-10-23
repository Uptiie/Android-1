package com.Lambda.rv_camping.model

data class Properties (
    val id: Int,
    var property_name: String,
    var description: String,
    var address: String,
    var city: String,
    var state: String,
    var image: String,
    var price: Int,
    var rating: Number,
    var owner_id: Int
)