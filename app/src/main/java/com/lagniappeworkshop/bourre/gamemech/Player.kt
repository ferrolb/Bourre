package com.lagniappeworkshop.bourre.gamemech

class Player constructor(var playerName: String) {

    var currentHand: Hand = Hand(Suit.CLUBS, Suit.CLUBS, ArrayList())

    fun haveSuit(suit: Suit) : Boolean {
        return false
    }
    fun haveTrump(trumpSuit: Suit) : Boolean {
        return false
    }
    fun playCard(suit: Suit, trumpSuit:Suit) : Card {
        // TODO: asdf
        return Card(Suit.CLUBS, 2, false)
    }
}