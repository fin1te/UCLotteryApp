package com.utkarsha.lotteryapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.utkarsha.lotteryapp.R
import com.utkarsha.lotteryapp.databinding.ActivityRegisterBinding
import com.utkarsha.lotteryapp.utils.SeedWordGenerator

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccountButton.setOnClickListener {
            createAccount()
        }

        binding.loginTextView.setOnClickListener {
            goToLoginActivity()
        }
    }

    private fun createAccount() {
        // check if the user has entered all the details and if the passwords match and if the password is strong enough
        val password1 = binding.passwordEditText.text.toString()
        val password2 = binding.confirmPasswordEditText.text.toString()

        if(password1.isEmpty() || password2.isEmpty()) {
            Toast.makeText(this, "Please enter both password", Toast.LENGTH_SHORT).show()
        } else if (password1 == password2) {
            // check if the password is strong enough
            if(password1.length < 8) {
                Toast.makeText(this, "Password should be at least 8 characters long", Toast.LENGTH_SHORT).show()
                return
            } else {
                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()

                val seedWords = SeedWordGenerator.randomSeedWords()

                val intent = Intent(this, WordActivity::class.java)
                intent.putExtra("seedWords", seedWords)
                startActivity(intent)
                binding.passwordEditText.text.clear()
                binding.confirmPasswordEditText.text.clear()
            }
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        // clear the activity stack
        //finishAffinity()
    }
}