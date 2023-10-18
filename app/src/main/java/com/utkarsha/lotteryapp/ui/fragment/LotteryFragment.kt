package com.utkarsha.lotteryapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.utkarsha.lotteryapp.R
import com.utkarsha.lotteryapp.adapter.LotteryAdapter
import com.utkarsha.lotteryapp.databinding.FragmentLotteryBinding
import com.utkarsha.lotteryapp.model.Jackpot

class LotteryFragment : Fragment() {

    private var binding: FragmentLotteryBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var database : FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentLotteryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.database

        recyclerView = binding!!.lotteryRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = LotteryAdapter(emptyList())

        getLotteryData()
    }

    private fun getLotteryData() {
        val myRef = database.getReference("jackpots")
        myRef.get().addOnSuccessListener {
            if(it.exists()) {
                val list = mutableListOf<Jackpot>()
                for (jackpot in it.children) {
                    val jackpotName = jackpot.child("jackpotName").value.toString()
                    val jackpotDescription = jackpot.child("jackpotDescription").value.toString()
                    val jackpotPrice = jackpot.child("jackpotPrice").value.toString()
                    val ticketPrice = jackpot.child("ticketPrice").value.toString()
                    val createdBy = jackpot.child("createdBy").value.toString()
                    val jackpotId = jackpot.child("jackpotId").value.toString()
                    val jackpot = Jackpot(jackpotId, jackpotName, jackpotDescription, jackpotPrice, ticketPrice, createdBy)
                    list.add(jackpot)
                }
                recyclerView.adapter = LotteryAdapter(list)
                binding?.noLotteryTextView?.visibility = View.GONE
                binding?.lotteryRecyclerView?.visibility = View.VISIBLE
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

}