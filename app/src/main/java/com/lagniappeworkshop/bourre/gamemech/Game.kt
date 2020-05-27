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
            for (rank in 2 until 14) {
                deck.add(Card(it, rank, false))
            }
        }
    }

    private fun shuffleCards () {
        deck.shuffle()
        deck.shuffle()
        deck.shuffle()
    }

    private fun dealToPlayer(player:Player) {
        player.currentHand.add(deck.removeAt(0))
    }

    private fun dealCards() {
        var playerCounter = dealerIndex+1
        for (cardNumber in 1..5) {
            do {
                val nextPlayer = (playerCounter++) % players.size
                dealToPlayer(players[nextPlayer])
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

    private fun dealerPlaysAndSetsTrumpAndSuit() {
        // dealer plays card
        val card = players[dealerIndex].playAnyCard()
        card.faceUp = true
        val player = players[dealerIndex]
        Log.i("########", "Dealer is ${player.playerName} and he plays ${card.faceValue()}")
        handInPlay[player] = card

        // dealer sets trump and suit
        trumpSuit = card.suit
        suit = card.suit
        Log.i("########", "Dealer sets trumpSuit ${trumpSuit.suitName} and suit ${suit.suitName}")
    }

    private fun playTrick() {
        dealerPlaysAndSetsTrumpAndSuit()
        otherPlayersPlayTrick()
    }

    private fun otherPlayersPlayTrick() {
        var playerCounter = dealerIndex+1
        do {
            val nextPlayer = (playerCounter++) % players.size
            // player plays card
            val card = players[nextPlayer].playCardToWin()
            Log.i("#########", "${players[nextPlayer].playerName} plays ${card.faceValue()}")
            card.faceUp = true
            handInPlay[players[nextPlayer]] = card
        } while (players[nextPlayer] != players[dealerIndex])
    }

    private fun showHandInPlay() {
        for ((player, card) in handInPlay) {
            Log.i("#########", "${player.playerName} played ${card.faceValue()}")
        }
    }

    fun playGame() {
        shuffleCards()
        dealCards()
        showCards()
        playTrick()
        showHandInPlay()
        
    }
}