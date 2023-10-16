package com.utkarsha.lotteryapp.utils

object WalletAddressGenerator {

    fun generateWalletAddress(): String {
        val hexChars = "0123456789abcdef"
        val sb = StringBuilder("0x")

        repeat(40) {
            val randomIndex = (hexChars.indices).random()
            sb.append(hexChars[randomIndex])
        }

        return sb.toString()
    }
}