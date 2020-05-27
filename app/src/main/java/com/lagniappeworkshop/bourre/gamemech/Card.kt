package com.lagniappeworkshop.bourre.gamemech

data class Card(val suit:Suit, val rank:Int, var faceUp:Boolean=false) {
    fun rankName() = run {
        when(rank) {
            in 2..10 -> rank.toString()
            11 -> "Jack"
            12 -> "Queen"
            13 -> "King"
            14 -> "Ace"
            else -> "Invalid"
        }
    }
    fun suitName() = suit.suitName
    fun faceValue() = rankName() + " of " + suitName()
}
enum class Suit(val suitName: String) {
    HEARTS("Hearts"),
    CLUBS("Clubs"),
    SPADES("Spades"),
    DIAMONDS("Diamonds")
}