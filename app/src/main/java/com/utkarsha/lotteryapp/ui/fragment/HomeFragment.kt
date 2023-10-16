package com.utkarsha.lotteryapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utkarsha.lotteryapp.databinding.FragmentHomeBinding
import com.utkarsha.lotteryapp.utils.SharedPrefsUtil


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

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

        val walletAddress = SharedPrefsUtil.getString("walletAddress", "walletAddress", requireContext())
        Log.d("Testlog", "Wallet Address: $walletAddress")
        binding?.textHome?.text = walletAddress
    }

}