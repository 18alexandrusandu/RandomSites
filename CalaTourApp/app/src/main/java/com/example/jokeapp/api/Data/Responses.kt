package com.example.jokeapp.api.Data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data  class ResponseJokes(

    @Json(name="error")
    val error:Boolean,
    @Json(name="amount")
    val amount:Int,
    @Json(name="jokes")
    val jokes:List<JokeMessage>

)



@JsonClass(generateAdapter = true)
open class JokeMessage (
    @Json(name = "category")
    val category: String,

    @Json(name = "type")
    val type: String,

    @Json(name = "safe")
    val safe: Boolean,

    @Json(name = "id")
    val id: Int,

    @Json(name = "lang")
    val lang: String,
    @Json(name="joke")
    val joke:String?=null,
    @Json(name = "setup")
    val setup: String?=null,
    @Json(name = "delivery")
    val delivery: String?=null
)
{

}
