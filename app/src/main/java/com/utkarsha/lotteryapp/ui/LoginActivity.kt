package com.utkarsha.lotteryapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utkarsha.lotteryapp.R
import com.utkarsha.lotteryapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}