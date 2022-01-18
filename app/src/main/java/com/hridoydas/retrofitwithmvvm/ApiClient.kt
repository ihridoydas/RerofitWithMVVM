package com.hridoydas.retrofitwithmvvm

import retrofit2.Response


class ApiClient(

    private val rickAndMortyService: RickAndMortyService

) {
    suspend fun getCharacterById(characterId: Int):SimpleResponse<GetCharacterByIdResponse>{

        return safeApiCall { rickAndMortyService.getCheracterById(characterId) }

    }
    private inline fun <T> safeApiCall(apiCall:()->Response<T>):SimpleResponse<T>{

        return try {
            SimpleResponse.success(apiCall.invoke())

        }catch (e:Exception){
            SimpleResponse.failure(e)
        }

    }

}