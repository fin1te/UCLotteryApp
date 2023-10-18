package com.utkarsha.lotteryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.utkarsha.lotteryapp.R
import com.utkarsha.lotteryapp.model.Jackpot

class LotteryAdapter(private val list : List<Jackpot>) : Adapter<LotteryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lottery, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val jackpot = list[position]

            holder.apply {
                title.text = jackpot.jackpotName
                desc.text = jackpot.jackpotDescription
                jackpotPrice.text = "Jackpot Price: ${jackpot.jackpotPrice} ETH"
                ticketPrice.text = "Ticket Price: ${jackpot.ticketPrice} ETH"
                createdby.text = "Created By: ${jackpot.createdBy}"
            }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.jackpotNameTextView)
        val desc = itemView.findViewById<TextView>(R.id.jackpotDescriptionTextView)
        val jackpotPrice = itemView.findViewById<TextView>(R.id.jackpotPriceTextView)
        val ticketPrice  = itemView.findViewById<TextView>(R.id.ticketPriceTextView)
        val createdby = itemView.findViewById<TextView>(R.id.createdByTextView)
    }
}