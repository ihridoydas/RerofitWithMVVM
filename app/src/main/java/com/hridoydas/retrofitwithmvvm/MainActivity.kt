package com.hridoydas.retrofitwithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView =findViewById<TextView>(R.id.textView)

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit:Retrofit=Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val rickAndMortyService : RickAndMortyService = retrofit.create(RickAndMortyService::class.java)

        rickAndMortyService.getCheracterById(12).enqueue(object :Callback<GetCharacterByIdResponse>{
            override fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
                //Log.d("MainAcivity",response.toString())
                if(!response.isSuccessful){

                    Toast.makeText(this@MainActivity,"Unsuccessful network call",Toast.LENGTH_SHORT).show()
                    return

                }

               /* val body = response.body()!!
                val name = body.name*/

                val body = response.body()!!
                val name = body.name

                textView.text = name
            }

            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                Log.d("MainAcivity",t.message ?:"Null Message".toString())
            }

        })
    }
}