package com.utkarsha.lotteryapp.model

data class Jackpot(
    val jackpotId: String = "",
    val jackpotName: String = "",
    val jackpotDescription: String = "",
    val jackpotPrice: String = "",
    val ticketPrice: String = "",
    val createdBy : String = ""
)
