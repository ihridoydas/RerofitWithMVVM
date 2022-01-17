package com.hridoydas.retrofitwithmvvm

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.sql.RowId

interface RickAndMortyService {

    @GET("character/{character-id}")
   suspend fun getCheracterById(

        //Define a path
        @Path("character-id") characterId: Int
    ): Response<GetCharacterByIdResponse>

}