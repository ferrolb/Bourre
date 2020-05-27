package com.lagniappeworkshop.bourre.gamemech

class Player constructor(var playerName: String, val game: Game) {

    var currentHand = ArrayList<Card>()

    fun haveSuit(suit: Suit) : Boolean {
        return currentHand.count{it.suit == suit} > 0
    }

    fun haveTrump(trumpSuit: Suit) : Boolean {
        return currentHand.count{it.suit == trumpSuit} > 0
    }

    fun playAnyCard() : Card = currentHand.removeAt(0)

    fun playHighestSuitCard(suit: Suit) : Card {
        val sortedList = currentHand.filter{it.suit == suit}.sortedBy { it.rank }
        val indexOfCardToPlay = currentHand.indexOf(sortedList[0])
        return currentHand.removeAt(indexOfCardToPlay)
    }

    fun playCardToWin() : Card =
        when {
            haveSuit(game.suit) -> {
                playHighestSuitCard(game.suit)
            }
            haveTrump(game.trumpSuit) -> {
                playHighestSuitCard(game.trumpSuit)
            }
            else -> {
                playAnyCard()
            }
        }
}