package com.utkarsha.lotteryapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.utkarsha.lotteryapp.R
import com.utkarsha.lotteryapp.databinding.FragmentHomeBinding
import com.utkarsha.lotteryapp.model.Jackpot
import com.utkarsha.lotteryapp.utils.SharedPrefsUtil


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var database: FirebaseDatabase
    private var walletAddress: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.database

        walletAddress = SharedPrefsUtil.getString("walletAddress", "walletAddress", requireContext())!!
        Log.d("Testlog", "Wallet Address: $walletAddress")

        // check for the balance of the wallet address in the database
        val myRef = database.getReference("users")
        myRef.get().addOnSuccessListener { it ->
            if(it.exists()) {
                for (user in it.children) {
                    if (user.child("walletAddress").value.toString() == walletAddress) {
                        val balance = user.child("walletBalance").value.toString()
                        binding?.walletBalanceTextView?.text = "Wallet Balance: $balance ETH"
                    }
                }
            }
        }

        binding?.walletTextView?.text = "Wallet Address: \n$walletAddress"

        val avatar = listOf(R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3)
        Glide.with(this).load(avatar.random()).circleCrop().into(binding!!.profileImageView)

        binding?.createJackpotButton?.setOnClickListener {
            createJackpot()
        }

    }

    private fun createJackpot() {
        // check if the user has entered all the details and if the passwords match and if the password is strong enough
        val jackpotName = binding?.titleEditText?.text.toString()
        val jackpotPrice = binding?.jackpotPriceEditText?.text.toString()
        val ticketPrice = binding?.ticketPriceEditText?.text.toString()
        val description = binding?.descriptionEditText?.text.toString()

        if (jackpotName.isEmpty() || jackpotPrice.isEmpty() || ticketPrice.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter all the details", Toast.LENGTH_SHORT).show()
            return
        } else {
            //val database = Firebase.database
            val myRef = database.getReference("jackpots")
            val jackpotId = myRef.push().key!!
            val jackpot = Jackpot(jackpotId, jackpotName, description, jackpotPrice, ticketPrice, walletAddress)
            myRef.child(jackpotId).setValue(jackpot)
            Log.d("Testlog", "Jackpot created successfully")

            Toast.makeText(requireContext(), "Jackpot created successfully", Toast.LENGTH_SHORT).show()
            binding?.titleEditText?.setText("")
            binding?.jackpotPriceEditText?.setText("")
            binding?.ticketPriceEditText?.setText("")
            binding?.descriptionEditText?.setText("")
        }
    }

}