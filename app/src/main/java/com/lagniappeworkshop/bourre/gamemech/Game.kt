package com.lagniappeworkshop.bourre.gamemech

import android.util.Log

class Game {
    private val players : ArrayList<Player> = arrayListOf(
        Player("Player 1"),
        Player("Player 2"),
        Player("Player 3"))

    private var deck: ArrayList<Card> = ArrayList()
    var discardDeck: ArrayList<Card> = ArrayList()
    var handInPlay: MutableMap<Player, Card> = HashMap()
    private var dealerIndex: Int

    init {
        createCards()
        dealerIndex = 0
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
        player.currentHand.cards.add(deck.removeAt(0))
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
            for (card in player.currentHand.cards) {
                Log.i("#########", "\t${card.faceValue()}")
            }
        }
    }

    private fun dealerPlaysAndSetsTrumpAndSuit() {
        // dealer plays card
        val card = players[dealerIndex].playAnyCard()
        card.faceUp = true
        val player = players[dealerIndex]
        handInPlay[player] = card

        // dealer sets trump and suit
        var playerCounter = dealerIndex+1
        do {
            val nextPlayer = (playerCounter++) % players.size
            players[nextPlayer].currentHand.trumpSuit = card.suit
            players[nextPlayer].currentHand.suit = card.suit
        } while (players[nextPlayer] != players[dealerIndex])
    }

    fun playTrick() {
        dealerPlaysAndSetsTrumpAndSuit()
        otherPlayersPlayTrick()
    }

    fun otherPlayersPlayTrick() {
        var playerCounter = dealerIndex+1
        do {
            val nextPlayer = (playerCounter++) % players.size
            // player plays card
            val card = players[nextPlayer].playCardToWin()
            card.faceUp = true
            handInPlay[players[nextPlayer]] = card
        } while (players[nextPlayer] != players[dealerIndex])
    }

    fun playGame() {
        shuffleCards()
        dealCards()
        showCards()
        playTrick()

    }
}