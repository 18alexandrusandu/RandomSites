package com.example.jokeapp.offerdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.jokeapp.models.Joke
import com.example.jokeapp.repository.FavoritesRepository
import com.example.jokeapp.repository.FavoritesRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoritesViewModel(
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    private val repository: FavoritesRepository=FavoritesRepositoryImpl
    private val _viewState = MutableStateFlow(FavoritesViewState(

        jokes = repository.getFavorites(),
        isListEmpty = false


    ))
    val viewState = _viewState.asStateFlow()

fun autoUpdate()
{
   _viewState.update {
       it.copy(
           jokes = repository.getFavorites()
       )

   }
    if(viewState.value.jokes.isEmpty())
    {
        _viewState.update {
            it.copy(
                isListEmpty = true
            )
        }
    }else{
        _viewState.update {
            it.copy(
                isListEmpty = false
            )
        }
    }
}
fun onClearFavorites()
{

    _viewState.update{

        it.copy(
            jokes= repository.resetList()

        )
    }

}
    fun onRemoveFavorites(id:Int)
    {
        _viewState.update{

           it.copy(
              jokes= repository.removeJokeById(id)

           )
        }
    }
    fun onAddFavorites(joke:Joke)
    {
        _viewState.update{

            it.copy(
                jokes= repository.addJokeAtEnd(joke)

            )
        }
    }

}


data class FavoritesViewState(
    val jokes: List<Joke>,
    val isListEmpty:Boolean
)