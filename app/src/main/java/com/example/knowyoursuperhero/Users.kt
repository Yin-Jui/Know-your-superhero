package com.example.knowyoursuperhero

data class Users(val hero:String, var username:String, val imageUrl: String, var location: latlng) {
    constructor(): this("", "", "", latlng())

}