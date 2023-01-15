package com.example.jokeapp.repository

interface AuthentificationRepository {

    var token:String
    var displayName:String
   suspend fun  login(username:String,password:String):Boolean
   suspend fun logout():Boolean

  suspend  fun globalLogout(username:String,password:String):Boolean
}


object AuthentificationRepositoryImpl:AuthentificationRepository
{

    override var token:String=""
    override  var displayName:String=""

    override suspend fun login(username: String, password: String): Boolean {

        return true;

    }
    override suspend fun logout(): Boolean {

        return true;

    }
   override suspend  fun globalLogout(username:String,password:String):Boolean
    {
        return true;
    }


}