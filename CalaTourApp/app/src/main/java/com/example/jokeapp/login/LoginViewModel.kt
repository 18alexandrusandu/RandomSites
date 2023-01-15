package com.example.jokeapp.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val initialState = LoginViewState()
    private val _viewState = MutableStateFlow(initialState)
    val viewState = _viewState.asStateFlow()
    val adminUser="admin"
    val adminPassword="loveU"
    fun onUsernameChanged(newUsername: String) {
        _viewState.update { state ->
            state.copy(
                username = newUsername,
                action = null
            )
        }
    }

    fun onPasswordChanged(newUsername: String) {
        _viewState.update { state ->
            state.copy(
                password = newUsername,
                action = null
            )
        }
    }

    fun onLogin() {
        val username = _viewState.value.username
        val password = _viewState.value.password
        var ok=true
        var userInputError: InputErrorType? =null
        var passwordInputError:InputErrorType?=null
        _viewState.update {
            it.copy(
                action=null
            )

        }
        if(username.isEmpty()) {
            userInputError=InputErrorType.Empty

            ok=false
        }else
        if(username.length<3) {
            userInputError=InputErrorType.TooShort

            ok=false
        }
      //  else
          /*  if(username!=adminUser)
            {
                userInputError=InputErrorType.Invalid
                ok=false
            }

           */
        if(password.isEmpty()) {

            passwordInputError=InputErrorType.Empty

            ok=false
        }else
            if(password.length<3) {
                passwordInputError=InputErrorType.TooShort
                ok=false
        }
         else
             if(password!=adminPassword)
                {     passwordInputError=InputErrorType.Invalid
                    ok=false
                }



        if(ok) {
            _viewState.update { state ->
                state.copy(
                    action = LoginViewAction.LoggedIn
                )
            }
        }else
        {     _viewState.update { state ->
            state.copy(

                action = LoginViewAction.ShowInputErrors(
                    usernameError = userInputError ,
                    passwordError=passwordInputError)
            )
        }

        }
    }
}

data class LoginViewState(
    val username: String = "",
    val password: String = "",
    val action: LoginViewAction? = null
)


sealed class LoginViewAction {
    object LoggedIn: LoginViewAction()

    data class ShowInputErrors(
        val usernameError: InputErrorType? = null,
        val passwordError: InputErrorType? = null
    ): LoginViewAction()
}

sealed class InputErrorType {
    object Empty: InputErrorType()
    object TooShort: InputErrorType()
    object Invalid: InputErrorType()

}
