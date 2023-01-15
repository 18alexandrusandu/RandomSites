package com.example.jokeapp.models

open class Joke(id: Int, type: String, category:String,isFavorite: Boolean){
    var id: Int?=id
    var type: String?=type
    var category:String?=category
    var isFavorite: Boolean?=isFavorite
}