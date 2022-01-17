package com.hridoydas.retrofitwithmvvm

import retrofit2.Response
import java.sql.RowId

class ApiClient(

    private val rickAndMortyService: RickAndMortyService

) {
    suspend fun getCharacterById(characterId: Int):Response<GetCharacterByIdResponse>{

        return rickAndMortyService.getCheracterById(characterId)

    }

}