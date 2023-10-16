package com.utkarsha.lotteryapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.utkarsha.lotteryapp.MainActivity
import com.utkarsha.lotteryapp.R
import com.utkarsha.lotteryapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var database: FirebaseDatabase
    private var walletAddress = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database

        binding.loginButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        // check if the user has entered all the details and if the passwords match and if the password is strong enough
        val password = binding.passwordEditText.text.toString()
        val seedWords = binding.seedWordsEditText.text.toString()

        if (password.isEmpty() || seedWords.isEmpty()) {
            Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show()
            return
        } else {
            if (password.length < 8) {
                Toast.makeText(this, "Password should be at least 8 chars long", Toast.LENGTH_SHORT).show()
                return
            } else {

                //val database = Firebase.database
                val myRef = database.getReference("users")
                //check if the seed words and password match
                myRef.get().addOnSuccessListener {
                    if(it.exists()) {
                        for (user in it.children) {
                            if (user.child("seedWords").value.toString() == seedWords && user.child("password").value.toString() == password) {
                                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                                walletAddress = user.child("walletAddress").value.toString()
                                goToMainActivity()
                                return@addOnSuccessListener
                            }
                        }
                        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("walletAddress", walletAddress)
        startActivity(intent)
        finishAffinity()
    }
}