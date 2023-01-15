package com.example.jokeapp.repository


import com.example.jokeapp.api.Data.PiuApi
import com.example.jokeapp.models.Joke
import com.example.jokeapp.models.Joke1
import com.example.jokeapp.models.Joke2


interface  JokesRepository {
   fun getJokes():List<Joke>

    suspend  fun readAll(): List<Joke>
    suspend fun readChristmas(): List<Joke>
    suspend fun readSpooky(): List<Joke>
    suspend fun readPun(): List<Joke>
    suspend fun readDark(): List<Joke>
    suspend fun readMisc(): List<Joke>
    suspend fun readProgramming(): List<Joke>
     fun toggleIsFavorite(id:Int)
}

class JokesRepositoryImpl: JokesRepository {

    private val piuApi = PiuApi.create()
    private val messages = mutableListOf<Joke>()

    override fun getJokes(): List<Joke> = messages.toList()


    override suspend fun readAll(): List<Joke> {

       try {
           val remoteMsg = piuApi.readAny()
           System.out.println("Any message:")
           System.out.println(remoteMsg.toString())

           val jokes2 = remoteMsg.jokes
           val remoteJokes = jokes2.map {
               if (it.type == "single") {

                   val joke2 = it.joke?.let { it1 ->
                       Joke1(
                           id = it.id,
                           joke = it.joke,
                           type = it.type,
                           category = it.category,
                           isFavorite = false
                       )
                   }
                   joke2
               } else {

                   val joke2 = it.setup?.let { it1 ->
                       Joke2(
                           id = it.id,
                           setup = it1,
                           delivery = it.delivery,
                           type = it.type,
                           category = it.category,
                           isFavorite = false
                       )
                   }
                   joke2
               }


           }
           messages.apply {

               clear()
               for (e in remoteJokes)
                   if (e != null)
                       add(e)
           }
       }
       catch(e:Exception)
       {
           System.out.println("can't connect to database")
           throw e;
       }
           return messages
       }




    override suspend fun readChristmas(): List<Joke> {
      try {
          val remoteMsg = piuApi.readChristmas()
          System.out.println("Any message:")
          System.out.println(remoteMsg.toString())

          val jokes2 = remoteMsg.jokes
          val remoteJokes = jokes2.map {
              if (it.type == "single") {

                  val joke2 = it.joke?.let { it1 ->
                      Joke1(
                          id = it.id,
                          joke = it.joke,
                          type = it.type,
                          category = it.category,
                          isFavorite = false
                      )
                  }
                  joke2
              } else {

                  val joke2 = it.setup?.let { it1 ->
                      Joke2(
                          id = it.id,
                          setup = it1,
                          delivery = it.delivery,
                          type = it.type,
                          category = it.category,
                          isFavorite = false
                      )
                  }
                  joke2
              }


          }
          messages.apply {

              clear()
              for (e in remoteJokes)
                  if (e != null)
                      add(e)
          }
      }
          catch(e:Exception)
          {
              java.lang.System.out.println("can't connect to database")
              throw e;
          }

        return messages;

        }



    override suspend fun readSpooky(): List<Joke> {
       try {
           val remoteMsg = piuApi.readSpooky()
           System.out.println("Any message:")
           System.out.println(remoteMsg.toString())

           val jokes2 = remoteMsg.jokes
           val remoteJokes = jokes2.map {
               if (it.type == "single") {

                   val joke2 = it.joke?.let { it1 ->
                       Joke1(
                           id = it.id,
                           joke = it.joke,
                           type = it.type,
                           category = it.category,
                           isFavorite = false
                       )
                   }
                   joke2
               } else {

                   val joke2 = it.setup?.let { it1 ->
                       Joke2(
                           id = it.id,
                           setup = it1,
                           delivery = it.delivery,
                           type = it.type,
                           category = it.category,
                           isFavorite = false
                       )
                   }
                   joke2
               }

           }
           messages.apply {

               clear()
               for (e in remoteJokes)
                   if (e != null)
                       add(e)
           }

       }catch(e:Exception)
           {
               System.out.println("can't connect to database")
               throw e;
           }
        return messages

    }

    override suspend fun readPun(): List<Joke> {
        try {
            val remoteMsg = piuApi.readPun()
            System.out.println("Any message:")
            System.out.println(remoteMsg.toString())

            val jokes2 = remoteMsg.jokes
            val remoteJokes = jokes2.map {
                if (it.type == "single") {

                    val joke2 = it.joke?.let { it1 ->
                        Joke1(
                            id = it.id,
                            joke = it.joke,
                            type = it.type,
                            category = it.category,
                            isFavorite = false
                        )
                    }
                    joke2
                } else {

                    val joke2 = it.setup?.let { it1 ->
                        Joke2(
                            id = it.id,
                            setup = it1,
                            delivery = it.delivery,
                            type = it.type,
                            category = it.category,
                            isFavorite = false
                        )
                    }
                    joke2
                }

            }
            messages.apply {

                clear()
                for (e in remoteJokes)
                    if (e != null)
                        add(e)
            }
        } catch(e:Exception)
        {
            System.out.println("can't connect to database")
            throw e;
        }

        return messages
    }

    override suspend fun readDark(): List<Joke> {
        try {
            val remoteMsg = piuApi.readDark()
            System.out.println("Any message:")
            System.out.println(remoteMsg.toString())

            val jokes2 = remoteMsg.jokes
            val remoteJokes = jokes2.map {
                if (it.type == "single") {

                    val joke2 = it.joke?.let { it1 ->
                        Joke1(
                            id = it.id,
                            joke = it.joke,
                            type = it.type,
                            category = it.category,
                            isFavorite = false
                        )
                    }
                    joke2
                } else {

                    val joke2 = it.setup?.let { it1 ->
                        Joke2(
                            id = it.id,
                            setup = it1,
                            delivery = it.delivery,
                            type = it.type,
                            category = it.category,
                            isFavorite = false
                        )
                    }
                    joke2
                }

            }
            messages.apply {

                clear()
                for (e in remoteJokes)
                    if (e != null)
                        add(e)
            }
        } catch(e:Exception)
        {
            System.out.println("can't connect to database");
            throw e;
        }

        return messages
    }

    override suspend fun readMisc(): List<Joke> {

        try {

            val remoteMsg = piuApi.readMisc()
            System.out.println("Any message:")
            System.out.println(remoteMsg.toString())

            val jokes2 = remoteMsg.jokes
            val remoteJokes = jokes2.map {
                if (it.type == "single") {

                    val joke2 = it.joke?.let { it1 ->
                        Joke1(
                            id = it.id,
                            joke = it.joke,
                            type = it.type,
                            category = it.category,
                            isFavorite = false
                        )
                    }
                    joke2
                } else {

                    val joke2 = it.setup?.let { it1 ->
                        Joke2(
                            id = it.id,
                            setup = it1,
                            delivery = it.delivery,
                            type = it.type,
                            category = it.category,
                            isFavorite = false
                        )
                    }
                    joke2
                }

            }
            messages.apply {

                clear()
                for (e in remoteJokes)
                    if (e != null)
                        add(e)
            }
        }catch(e:Exception)
        {
            System.out.println("can't connect to data source");
            throw e;
        }


        return messages;
        }




    override suspend fun readProgramming(): List<Joke> {



           try {
               val remoteMsg = piuApi.readProgramming()

            System.out.println("Any message:")
            System.out.println(remoteMsg.toString())

            val jokes2 = remoteMsg.jokes
            val remoteJokes = jokes2.map {
                if (it.type == "single") {

                    val joke2 = it.joke?.let { it1 ->
                        Joke1(
                            id = it.id,
                            joke = it.joke,
                            type = it.type,
                            category = it.category,
                            isFavorite = false
                        )
                    }
                    joke2
                } else {

                    val joke2 = it.setup?.let { it1 ->
                        Joke2(
                            id = it.id,
                            setup = it1,
                            delivery = it.delivery,
                            type = it.type,
                            category = it.category,
                            isFavorite = false
                        )
                    }
                    joke2
                }

            }
            messages.apply {

                clear()
                for (e in remoteJokes)
                    if (e != null)
                        add(e)
            }
           }catch(e:Exception)
           {
               System.out.println("can't connect to data source");
               throw e;
           }

        return messages
        }

    override fun toggleIsFavorite(id: Int) {
       val index=messages.indexOfFirst{it.id==id}

        if(messages.size>0 && index>=0) {

            val joke = messages[index]

            joke.isFavorite = !joke.isFavorite!!
            messages[index] = joke
        }


    }
}
