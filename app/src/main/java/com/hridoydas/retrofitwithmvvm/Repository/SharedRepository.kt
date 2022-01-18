package com.hridoydas.retrofitwithmvvm.Repository

import com.hridoydas.retrofitwithmvvm.network.NetworkLayer
import com.hridoydas.retrofitwithmvvm.network.response.GetCharacterByIdResponse


class SharedRepository {

    suspend fun getCharacterById( characterId: Int ): GetCharacterByIdResponse?{

        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if(request.failed){

            return null
        }
        if(!request.isSuccessful){
            return null
        }
        return request.body
    }

}