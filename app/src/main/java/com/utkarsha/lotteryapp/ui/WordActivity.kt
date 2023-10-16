package com.utkarsha.lotteryapp.ui

import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.utkarsha.lotteryapp.R
import com.utkarsha.lotteryapp.databinding.ActivityWordBinding
import com.utkarsha.lotteryapp.utils.SeedWordGenerator

class WordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val seedWords = intent.getStringExtra("seedWords")

        binding.wordEditText.setText(seedWords)

        binding.copyButton.setOnClickListener {
            // Copy the word to the clipboard
            val word = binding.wordEditText.text.toString()
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.text = word
            Toast.makeText(this, "Seed words copied to clipboard!", Toast.LENGTH_SHORT).show()
        }

        binding.doneButton.setOnClickListener {
            // toast and pop back to the previous activity
            Toast.makeText(this, "Seed words saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}