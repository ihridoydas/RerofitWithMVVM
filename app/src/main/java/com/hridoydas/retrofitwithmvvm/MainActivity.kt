package com.hridoydas.retrofitwithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel:SharedViewModel by lazy {

            ViewModelProvider(this).get(SharedViewModel::class.java)

        }

        val nameTextView =findViewById<AppCompatTextView>(R.id.nameTextView)
        val headerImageView = findViewById<AppCompatImageView>(R.id.headerImageView)
        val aliveTextView = findViewById<AppCompatTextView>(R.id.aliveTextView)
        val originTextView = findViewById<AppCompatTextView>(R.id.originTextView)
        val speciesTextView = findViewById<AppCompatTextView>(R.id.speciesTextView)
        val genderImageView = findViewById<AppCompatImageView>(R.id.genderImageView)


        viewModel.refreshCharacter(54)
        viewModel.characterByIdLiveData.observe(this){ response->
            if(response==null){
                Toast.makeText(this@MainActivity,
                    "Unsuccessful network call",
                    Toast.LENGTH_SHORT).show()

                return@observe
            }

            nameTextView.text = response.name
            aliveTextView.text = response.status
            originTextView.text = response.origin.name
            speciesTextView.text = response.species
            Picasso.get().load(response.image).into(headerImageView);

            if(response.gender.equals("male",true)){
                genderImageView.setImageResource(R.drawable.ic_male_24)
            }else{
                genderImageView.setImageResource(R.drawable.ic_female_24)

            }



        }

      //  NetworkLayer.apiClient.getCharacterById(54)

       /* rickAndMortyService.getCheracterById(3).enqueue(object :Callback<GetCharacterByIdResponse>{
            override fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
                //Log.d("MainAcivity",response.toString())
                if(!response.isSuccessful){

                    Toast.makeText(this@MainActivity,"Unsuccessful network call",Toast.LENGTH_SHORT).show()
                    return

                }

                val body = response.body()!!
                val name = body.name

                val body = response.body()!!
               //Now add UI for spacefic file

                nameTextView.text = body.name
                aliveTextView.text = body.status
                originTextView.text = body.origin.name
                speciesTextView.text = body.species
                Picasso.get().load(body.image).into(headerImageView);

                if(body.gender.equals("male",true)){
                    genderImageView.setImageResource(R.drawable.ic_male_24)
                }else{
                    genderImageView.setImageResource(R.drawable.ic_female_24)

                }

            }

            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                Log.d("MainAcivity",t.message ?:"Null Message".toString())
            }

        })*/
    }
}