package com.hridoydas.retrofitwithmvvm.network

import com.hridoydas.retrofitwithmvvm.network.response.GetCharacterByIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("character/{character-id}")
   suspend fun getCheracterById(

        //Define a path
        @Path("character-id") characterId: Int
    ): Response<GetCharacterByIdResponse>

}