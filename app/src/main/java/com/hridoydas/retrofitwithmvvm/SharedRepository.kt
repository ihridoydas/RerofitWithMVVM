package com.hridoydas.retrofitwithmvvm

import java.sql.RowId

class SharedRepository {

    suspend fun getCharacterById( characterId: Int ): GetCharacterByIdResponse?{

        val request = NetworkLayer.apiClient.getCharacterById(characterId)
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

}