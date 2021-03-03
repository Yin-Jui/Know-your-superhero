package com.example.knowyoursuperhero

data class Users(val hero:String, var username:String, val imageUrl: String) {
    constructor(): this("", "", "")
}