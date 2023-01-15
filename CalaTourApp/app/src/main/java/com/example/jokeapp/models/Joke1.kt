package com.example.jokeapp.models



open class Joke1
(
    val joke: String,
    id: Int,
    type: String,
    category:String,
    isFavorite:Boolean
    ):Joke(id, type, category, isFavorite )
