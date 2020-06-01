package com.lagniappeworkshop.bourre.gamemech

import android.util.Log

class Game {
    private lateinit var players : ArrayList<Player>

    private var deck: ArrayList<Card> = ArrayList()
//    var discardDeck: ArrayList<Card> = ArrayList()
    var handInPlay = HashMap<Player,Card>()
    private var dealerIndex: Int
    var trumpSuit: Suit
    var suit: Suit

    init {
        createCards()
        dealerIndex = 0
        trumpSuit = Suit.HEARTS
        suit = Suit.HEARTS
        players = arrayListOf(
            Player("Player 1", this),
            Player("Player 2", this),
            Player("Player 3", this))
    }

    private fun createCards() {
        deck.clear()
        enumValues<Suit>().forEach {
            for (rank in 2..14) {
                deck.add(Card(it, rank, false))
            }
        }
    }

    private fun shuffleCards () {
        deck.shuffle()
        deck.shuffle()
        deck.shuffle()
    }

    private fun dealCards() {
        var playerCounter = dealerIndex+1
        for (cardNumber in 1..5) {
            do {
                val nextPlayer = (playerCounter++) % players.size
                players[nextPlayer].currentHand.add(deck.removeAt(0))
            } while (players[nextPlayer] != players[dealerIndex])
        }
    }

    private fun showCards() {
        for (player in players) {
            Log.i("#########", "${player.playerName}:")
            for (card in player.currentHand) {
                Log.i("#########", "\t${card.faceValue()}")
            }
        }
    }

    private fun dealerSetsTrump() {
        // dealer shows trump
        val card = players[dealerIndex].currentHand[0]
        card.faceUp = true
        val player = players[dealerIndex]
        Log.i("########", "Dealer is ${player.playerName} and he shows trump card ${card.faceValue()}")

        // dealer sets trump
        trumpSuit = card.suit
        Log.i("########", "Dealer sets trumpSuit ${trumpSuit.suitName}")
        card.faceUp = false
    }

    private fun playersPlayTrick() {
        var playerCounter = dealerIndex+1
        // player next to dealer plays card and sets the suit
        var nextPlayer = (playerCounter++) % players.size
        val card = players[nextPlayer].playAnyCard()
        Log.i("#########", "${players[nextPlayer].playerName} plays ${card.faceValue()}, setting the suit")
        card.faceUp = true
        handInPlay[players[nextPlayer]] = card
        suit = card.suit
        do {
            nextPlayer = (playerCounter++) % players.size
            // player plays card
            val card = players[nextPlayer].playCardToWin()
            Log.i("#########", "${players[nextPlayer].playerName} plays ${card.faceValue()}")
            card.faceUp = true
            handInPlay[players[nextPlayer]] = card
            players[nextPlayer]
        } while (players[nextPlayer] != players[dealerIndex])
    }

    private fun showHandInPlay() {
        for ((player, card) in handInPlay) {
            Log.i("#########", "${player.playerName} played ${card.faceValue()}")
        }
    }

    private fun determineTrickWinner()  {
        // Highest Trump
        var highestCard = handInPlay.filter{ it.value.suit == trumpSuit }.maxBy { it.value.rank }
        if (highestCard == null) {
            // Highest Suit
            highestCard = handInPlay.filter{ it.value.suit == suit}.maxBy{it.value.rank}
            if (highestCard == null) {
                // Highest Other card
                highestCard = handInPlay.maxBy { it.value.rank }
            }
        }
        if (highestCard != null) {
            Log.i("#######", "${highestCard.key.playerName} wins with ${highestCard.value.faceValue()}")
        } else {
            Log.i("#######", "Error: no highest card found")
        }
    }

    // The trump and suit setting is done
    // TODO: fix bug having too many cards at start of 2nd trick
    // TODO: add trick loop to play all tricks
    // TODO: determine hand winner
    fun playGame() {
        shuffleCards()
        for (trick in 1..5) {
            Log.i("#######", "==== Trick $trick =====")
            dealCards()
            showCards()
            dealerSetsTrump()
            playersPlayTrick()
            showHandInPlay()
            determineTrickWinner()
            Log.i("#######", "=======================")
        }
    }
}