package com.example.jokeapp.models

open class Joke2 (

    val setup:String,
    val delivery: String?=null,
    id: Int,
    type: String,
    category: String,
    isFavorite: Boolean,

    ):Joke(id, type,category, isFavorite)