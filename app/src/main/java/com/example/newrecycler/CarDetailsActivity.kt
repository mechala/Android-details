package com.example.newrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.newrecycler.databinding.ActivityCarDetailsBinding
import kotlinx.android.synthetic.main.activity_car_details.*

class CarDetailsActivity : AppCompatActivity() {
    lateinit var binding : ActivityCarDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_car_details)

       car_name.text = getIntent().getStringExtra("CARNAME")
        car_description.text = getIntent().getStringExtra("CARDESC")

        var photo =(getIntent().getStringExtra("PHOTO"))
        Glide.with(this)
            .load(photo)
            .into(iv_foto)

    }
}
