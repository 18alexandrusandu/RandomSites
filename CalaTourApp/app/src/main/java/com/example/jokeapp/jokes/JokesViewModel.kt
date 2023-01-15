package com.example.jokeapp.jokes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapp.models.Joke

import com.example.jokeapp.repository.JokesRepository
import com.example.jokeapp.repository.JokesRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JokesViewModel(
    savedStateHandle: SavedStateHandle,

) : ViewModel() {
    private val repo: JokesRepository = JokesRepositoryImpl()
    var name="";
    private val _viewState = MutableStateFlow(
        JokesViewState(
           jokes = repo.getJokes()
        )
    )
    val viewState = _viewState.asStateFlow()
  init{

        name= savedStateHandle.get<String>("name")!!
        System.out.println("Name:"+name)

      viewModelScope.launch{
          _viewState.update{
              it.copy(
                  isLoading=true
              )
          }

          delay(3000L)
          _viewState.update{
              it.copy(
                  isLoading=false
              )
          }

      }


  }
    fun onReload()
    {
        viewModelScope.launch{
            _viewState.update{
                it.copy(
                    isLoading=true
                )
            }

            delay(3000L)
            _viewState.update{
                it.copy(
                    isLoading=false
                )
            }

        }
    }

    fun onReadAll()=viewModelScope.launch{
        onReload()
       try {
           repo.readAll()
           val nlist = repo.getJokes()
           _viewState.update {

               it.copy(jokes = nlist,ConnectionError=false)
           }
       }catch(e:Exception ){
           _viewState.update {

               it.copy(ConnectionError=true)
           }
       }
    }
     fun onReadChristmas()=viewModelScope.launch{
         onReload()
         try {
             repo.readChristmas()
             val nlist = repo.getJokes()
             _viewState.update {

                 it.copy(jokes = nlist,ConnectionError=false)
             }
         }catch(e:Exception ){
             _viewState.update {

                 it.copy(ConnectionError=true)
             }
         }
    }
     fun onReadSpooky()=viewModelScope.launch{
         onReload()
         try {
             repo.readSpooky()
             val nlist = repo.getJokes()
             _viewState.update {

                 it.copy(jokes = nlist,ConnectionError=false)
             }
         }catch(e:Exception ){
             _viewState.update {

                 it.copy(ConnectionError=true)
             }
         }
    }
   fun onReadPun()=viewModelScope.launch{
       onReload()
       try {
           repo.readPun()
           val nlist = repo.getJokes()
           _viewState.update {

               it.copy(jokes = nlist,ConnectionError=false)
           }
       }catch(e:Exception ){
           _viewState.update {

               it.copy(ConnectionError=true)
           }
       }
    }
    fun onReadDark()=viewModelScope.launch{
        onReload()
        try {
            repo.readDark()
            val nlist = repo.getJokes()
            _viewState.update {

                it.copy(jokes = nlist,ConnectionError=false)
            }
        }catch(e:Exception ){
            _viewState.update {

                it.copy(ConnectionError=true)
            }
        }
    }
     fun onReadMisc()=viewModelScope.launch{
         onReload()
         try {
             repo.readMisc()
             val nlist = repo.getJokes()
             _viewState.update {

                 it.copy(jokes = nlist,ConnectionError=false)
             }
         }catch(e:Exception ){
             _viewState.update {

                 it.copy(ConnectionError=true)
             }
         }
    }
    fun onReadProgramming()=viewModelScope.launch{
        onReload()
        try {
            repo.readProgramming()
            val nlist = repo.getJokes()
            _viewState.update {

                it.copy(jokes = nlist,ConnectionError=false)
            }
        }catch(e:Exception ){
            _viewState.update {

                it.copy(ConnectionError=true)
            }
        }
    }

  fun onPressMenuItem(index:Int)
  {
      _viewState.update {

          it.copy(previousOption =  _viewState.value.activeOption, activeOption = index)
      }


  }
  fun onMakeFavorite(id:Int)
    {
    repo.toggleIsFavorite(id)


    }
    fun onRemoveFavorite(id:Int)
    {
        repo.toggleIsFavorite(id)
    }


}

data class JokesViewState(
    val jokes: List<Joke>,
    val isLoading:Boolean=false,
    val activeOption:Int=0,
    val previousOption:Int=0,
    val ConnectionError:Boolean=false
)