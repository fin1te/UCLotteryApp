package com.utkarsha.lotteryapp.utils

object SeedWordGenerator {

    val randomWords = listOf(
        "apple",
        "banana",
        "chocolate",
        "dog",
        "elephant",
        "festival",
        "guitar",
        "happy",
        "island",
        "jazz",
        "kangaroo",
        "lemon",
        "mountain",
        "notebook",
        "ocean",
        "penguin",
        "quilt",
        "rainbow",
        "sunset",
        "tiger",
        "umbrella",
        "volcano",
        "waterfall",
        "xylophone",
        "yacht",
        "zebra",
        "beach",
        "candle",
        "dolphin",
        "eagle",
        "fireworks",
        "giraffe",
        "hamburger",
        "ice cream",
        "jungle",
        "koala",
        "lighthouse",
        "moonlight",
        "noodle",
        "orchid",
        "parrot",
        "quokka",
        "rainforest",
        "starfish",
        "toucan",
        "unicorn",
        "violin",
        "whale",
        "xylophone",
        "yogurt",
        "zeppelin"
    )


    fun randomSeedWords(): String {
        val seedWords = mutableListOf<String>()
        val maxIndex = randomWords.size - 1
        val random = java.util.Random()

        for (i in 1..12) {
            val randomIndex = random.nextInt(maxIndex + 1)
            seedWords.add(randomWords[randomIndex])
        }
        return seedWords.joinToString(" ")
    }

}