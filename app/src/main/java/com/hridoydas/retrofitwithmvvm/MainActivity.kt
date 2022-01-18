package com.hridoydas.retrofitwithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import retrofit2.*



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

    }
}