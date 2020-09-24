package br.com.fiap.nacmarvel.service

import br.com.fiap.nacmarvel.characters.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.security.MessageDigest

val PUBLIC_API_KEY = ""
val PRIVATE_API_KEY = ""

interface MarvelService {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String
    ): CharacterDataWrapper

    @GET("v1/public/characters/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int?,
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): CharacterDataWrapper
}

fun getApiHash(ts: String): String {
    val bytes = MessageDigest
        .getInstance("MD5")
        .digest("${ts}$PRIVATE_API_KEY$PUBLIC_API_KEY".toByteArray())

    return bytes.joinToString("") { "%02x".format(it) }
}