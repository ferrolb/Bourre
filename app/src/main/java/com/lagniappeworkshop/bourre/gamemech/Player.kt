package com.lagniappeworkshop.bourre.gamemech

class Player constructor(var playerName: String) {

    var currentHand: Hand = Hand(Suit.CLUBS, Suit.CLUBS, ArrayList())

    fun haveSuit(suit: Suit) : Boolean {
        return currentHand.cards.count{it.suit == suit} > 0
    }

    fun haveTrump(trumpSuit: Suit) : Boolean {
        return currentHand.cards.count{it.suit == trumpSuit} > 0
    }

    fun playAnyCard() : Card = currentHand.cards.removeAt(0)

    fun playHighestSuitCard(suit: Suit) : Card {
        val sortedList = currentHand.cards.filter{it.suit == suit}.sortedBy { it.rank }
        val indexOfCardToPlay = currentHand.cards.indexOf(sortedList[0])
        return currentHand.cards.removeAt(indexOfCardToPlay)
    }

    fun playCardToWin() : Card =
        when {
            haveSuit(currentHand.suit) -> {
                playHighestSuitCard(currentHand.suit)
            }
            haveTrump(currentHand.trumpSuit) -> {
                playHighestSuitCard(currentHand.trumpSuit)
            }
            else -> {
                playAnyCard()
            }
        }
}