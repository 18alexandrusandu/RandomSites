package com.example.jokeapp.repository

import com.example.jokeapp.models.Joke


interface FavoritesRepository {
    fun getFavorites() : List<Joke>

    fun getJokeById(id: Int): Joke?

    fun addJoke(index: Int = 0, offer: Joke): List<Joke>
    fun addJokeAtEnd(joke:Joke): List<Joke>
    fun removeJokeById(id: Int): List<Joke>

    fun resetList(): List<Joke>
    fun toggleIsFavorite(id: Int)
}

object FavoritesRepositoryImpl:  FavoritesRepository {

    private val initialJokes = listOf<Joke>()

    private val jokes = initialJokes.toMutableList()

    override fun getFavorites(): List<Joke> = jokes.toList()

    override fun getJokeById(id: Int): Joke? =
        jokes.firstOrNull {
            it.id == id
        }

    override fun addJoke(index: Int, joke: Joke): List<Joke>
    {
        if (!jokes.contains(joke)) {
           return  jokes.apply {
                add(index,joke)
            }.toList()
        }
        return jokes
    }
    override fun addJokeAtEnd(joke: Joke): List<Joke> {
        if (!jokes.contains(joke)) {
            return  jokes.apply {
                add(joke)
            }.toList()
        }
        return jokes
    }

    override fun removeJokeById(id: Int): List<Joke> =
        jokes.apply {
            removeIf { it.id == id }
        }.toList()

    override fun resetList(): List<Joke> =
        jokes.apply {
            clear()
        }.toList()

    override fun toggleIsFavorite(id: Int){

            val index = jokes.indexOfFirst { it.id == id }
        if(jokes.size>0 && index>=0 ) {
            val joke = jokes[index]

            joke.isFavorite = !joke.isFavorite!!
            if (joke.isFavorite == true)
                removeJokeById(id)


        }

    }

}
